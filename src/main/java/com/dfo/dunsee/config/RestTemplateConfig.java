package com.dfo.dunsee.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.core5.util.Timeout;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@Slf4j
public class RestTemplateConfig {

  @Value("${rest-template.timeout}")
  private int timeout;

  @Bean
  public RestTemplate restTemplate() {

    return new RestTemplate(httpRequestFactory());
  }

  private ClientHttpRequestFactory httpRequestFactory() {
    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();

    CloseableHttpClient httpClient = (CloseableHttpClient) setHttpClient(timeout);

    factory.setHttpClient(httpClient);
    return factory;
  }


  private HttpClient setHttpClient(int timeout) {
    RequestConfig requestConfig = setRequestConfig(timeout);
    ConnectionConfig connectionConfig = setConnectionConfig(timeout);

    return HttpClientBuilder.create()
                            .setDefaultRequestConfig(requestConfig)
                            .setConnectionManager(setConnectionManager(connectionConfig))
                            .build();

  }

  private RequestConfig setRequestConfig(int timeout) {
    return RequestConfig.custom()
                        .setConnectionRequestTimeout(Timeout.ofSeconds(timeout))  //ConnectionPool 에서 연결을 가져오는데 걸리는 최대시간
                        .build();
  }

  private ConnectionConfig setConnectionConfig(int timeout) {
    return ConnectionConfig.custom()
                           .setConnectTimeout(Timeout.ofSeconds(timeout)) //서버에 연결시도하는데 걸리는 최대시간
                           .setSocketTimeout(Timeout.ofSeconds(timeout))  //연결 후 데이터 송수신 최대시간
                           .build();
  }


  private HttpClientConnectionManager setConnectionManager(ConnectionConfig connectionConfig) {
    PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();

    poolingHttpClientConnectionManager.setDefaultConnectionConfig(connectionConfig);
    poolingHttpClientConnectionManager.setDefaultMaxPerRoute(500); //한 호스트에 열리는 최대 연결 수
    poolingHttpClientConnectionManager.setMaxTotal(250); //ConnectionPool 최대 Connection

    return poolingHttpClientConnectionManager;
  }
}

