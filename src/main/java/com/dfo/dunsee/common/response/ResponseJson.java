package com.dfo.dunsee.common.response;

import com.dfo.dunsee.common.ResultType;
import com.dfo.dunsee.common.ServiceCode;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseJson {

  private String code;
  private String description;
  private String detail;
  private InnerData data;

  public static ResponseJson setResponseJson(ServiceCode serviceCode, ResultType resultType, String details) {
    return ResponseJson.builder()
        .code(serviceCode.name())
        .detail(details)
        .description(serviceCode.getDescription())
        .data(new InnerData(resultType.name()))
        .build();
  }
}
