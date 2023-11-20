package com.dfo.dunsee.config;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

  @Value("${rest-template.timeout}")
  private int timeout;

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {

    return restTemplateBuilder.setConnectTimeout(Duration.ofSeconds(timeout))
                              .setReadTimeout(Duration.ofSeconds(timeout))
                              .build();
  }

}
