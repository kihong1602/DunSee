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
  private InnerData data;

  public static ResponseJson setResponseJson(ServiceCode serviceCode, ResultType resultType) {
    return ResponseJson.builder()
                       .code(serviceCode.name())
                       .description(serviceCode.getDescription())
                       .data(new InnerData(resultType.name()))
                       .build();
  }
}
