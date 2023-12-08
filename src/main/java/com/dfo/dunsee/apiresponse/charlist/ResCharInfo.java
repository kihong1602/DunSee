package com.dfo.dunsee.apiresponse.charlist;

import com.dfo.dunsee.apiresponse.ApiResponse;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ResCharInfo implements ApiResponse {

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
