package com.dfo.dunsee.common;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiUtils {

  private final RestTemplate restTemplate;

  public ApiUtils(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public <T> T getApiResponseJson(String searchUrl, Class<T> className) {
    return restTemplate.getForObject(searchUrl, className);
  }
}
