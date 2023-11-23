package com.dfo.dunsee.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class ApiUtils {

  private final RestTemplate restTemplate;

  public ApiUtils(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public <T> T getApiResponseJson(ServiceCode serviceCode, String searchUrl, Class<T> className) {
    log.info(ServiceCode.setServiceMsg(serviceCode) + "Get API Data");
    return restTemplate.getForObject(searchUrl, className);
  }
}
