package com.dfo.dunsee.response.charlist;

import com.dfo.dunsee.response.ApiResponse;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ResponseCharacterInfo implements ApiResponse {

  private String serverId;
  private String characterId;
  private String characterName;
  private int level;
  private String jobId;
  private String jobGrowId;
  private String jobName;
  private String jobGrowName;
  private Integer fame;

}
