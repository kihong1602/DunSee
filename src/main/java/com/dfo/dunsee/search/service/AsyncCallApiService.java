package com.dfo.dunsee.search.service;

import com.dfo.dunsee.apiresponse.ApiResponse;
import com.dfo.dunsee.common.ApiUtils;
import com.dfo.dunsee.common.ServiceCode;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AsyncCallApiService {

  private final ApiUtils apiUtils;

  @Async
  public CompletableFuture<ApiResponse> callNeopleApi(ServiceCode serviceCode, String url,
      Class<? extends ApiResponse> classType) {
    log.info(ServiceCode.setServiceMsg(serviceCode) + "API호출 비동기 처리");
    ApiResponse result = apiUtils.getApiResponseJson(serviceCode, url, classType);
    return CompletableFuture.completedFuture(result);
  }
}
