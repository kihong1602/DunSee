package com.dfo.dunsee.domain.search.dto.detail;

import com.dfo.dunsee.apiresponse.charstatus.Status;
import lombok.Getter;

@Getter
public class StatusDto {

  private String name;
  private String value;

  private StatusDto(String name, String value) {
    this.name = name;
    this.value = value;
  }

  public static StatusDto createStatusDto(Status status) {
    return new StatusDto(status.getName(), String.valueOf(status.getValue()));
  }
}
