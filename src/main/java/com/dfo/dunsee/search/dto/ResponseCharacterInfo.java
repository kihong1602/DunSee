package com.dfo.dunsee.search.dto;

import lombok.Data;

@Data
public class ResponseCharacterInfo {

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
