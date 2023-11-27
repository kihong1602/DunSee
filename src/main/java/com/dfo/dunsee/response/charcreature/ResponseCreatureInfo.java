package com.dfo.dunsee.response.charcreature;

import com.dfo.dunsee.response.ApiResponse;
import lombok.Data;

@Data
public class ResponseCreatureInfo implements ApiResponse {

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
  private Creature creature;

}
