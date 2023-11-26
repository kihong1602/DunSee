package com.dfo.dunsee.config;

import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@Slf4j
public class RestTemplateConfig {

  @Value("${rest-template.connection-request-timeout}")
  private int connectionRequestTimeout;

  @Value("${rest-template.connect-timeout}")
  private int connectTimeout;

  @Value("${rest-template.socket-timeout}")
  private int socketTimeout;

  @Value("${rest-template.max-connection}")
  private int maxConnection;
  @Value("${rest-template.max-per-route}")
  private int maxPerRoute;

  @Bean
  public RestTemplate restTemplate() {

    return new RestTemplate(httpRequestFactory());
  }

  private ClientHttpRequestFactory httpRequestFactory() {
    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();

    CloseableHttpClient httpClient = (CloseableHttpClient) setHttpClient();

    factory.setHttpClient(httpClient);
    return factory;
  }


  private HttpClient setHttpClient() {
    RequestConfig requestConfig = setRequestConfig();
    ConnectionConfig connectionConfig = setConnectionConfig();

    return HttpClientBuilder.create()
                            .setDefaultRequestConfig(requestConfig)
                            .setConnectionManager(setConnectionManager(connectionConfig))
                            .build();

  }

  private RequestConfig setRequestConfig() {
    return RequestConfig.custom()
                        .setConnectionRequestTimeout(connectionRequestTimeout,
                                                     TimeUnit.SECONDS)  //ConnectionPool 에서 연결을 가져오는데 걸리는 최대시간
                        .build();
  }

  private ConnectionConfig setConnectionConfig() {
    return ConnectionConfig.custom()
                           .setConnectTimeout(connectTimeout, TimeUnit.SECONDS)  //서버에 연결시도하는데 걸리는 최대시간
                           .setSocketTimeout(socketTimeout, TimeUnit.SECONDS) //연결 후 데이터 송수신 최대시간
                           .build();
  }


  private HttpClientConnectionManager setConnectionManager(ConnectionConfig connectionConfig) {
    PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();

    poolingHttpClientConnectionManager.setDefaultConnectionConfig(connectionConfig);
    poolingHttpClientConnectionManager.setDefaultMaxPerRoute(maxPerRoute); //한 호스트에 열리는 최대 연결 수
    poolingHttpClientConnectionManager.setMaxTotal(maxConnection); //ConnectionPool 최대 Connection

    return poolingHttpClientConnectionManager;
  }
}

