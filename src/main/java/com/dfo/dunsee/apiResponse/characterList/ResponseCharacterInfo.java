package com.dfo.dunsee.apiResponse.characterList;

import com.dfo.dunsee.apiResponse.ApiResponse;
import lombok.Data;

@Data
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
