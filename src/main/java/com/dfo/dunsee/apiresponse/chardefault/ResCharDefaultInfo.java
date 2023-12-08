package com.dfo.dunsee.apiresponse.chardefault;

import com.dfo.dunsee.apiresponse.ApiResponse;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ResCharDefaultInfo implements ApiResponse {

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
