package com.dfo.dunsee.apiResponse.characterDefault;

import com.dfo.dunsee.apiResponse.ApiResponse;
import lombok.Data;

@Data
public class ResponseCharacterDefaultInfo implements ApiResponse {

  private String characterId;
  private String characterName;
  private int level;
  private String jobId;
  private String jobGrowId;
  private String jobName;
  private String jobGrowName;
  private String adventureName;
  private String guildId;
  private String guildName;

}
