package com.dfo.dunsee.common;

import com.dfo.dunsee.apiresponse.ApiResponse;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
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

  @Async
  public CompletableFuture<ApiResponse> callNeopleApi(ServiceCode serviceCode, String url,
      Class<? extends ApiResponse> classType) {
    log.info(ServiceCode.setServiceMsg(serviceCode) + "API호출 비동기 처리");
    ApiResponse result = getApiResponseJson(serviceCode, url, classType);
    return CompletableFuture.completedFuture(result);
  }
}
