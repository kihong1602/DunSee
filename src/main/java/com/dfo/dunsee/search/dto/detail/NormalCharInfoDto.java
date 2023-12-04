package com.dfo.dunsee.search.dto.detail;

import lombok.Builder;
import lombok.Getter;

@Getter
public class NormalCharInfoDto {

  private String imgUrl;
  private String characterName;
  private int level;
  private String jobGrowName;
  private String guildName;
  private String adventureName;

  @Builder
  public NormalCharInfoDto(String imgUrl, String characterName, int level, String jobGrowName, String guildName,
      String adventureName) {
    this.imgUrl = imgUrl;
    this.characterName = characterName;
    this.level = level;
    this.jobGrowName = jobGrowName;
    this.guildName = guildName;
    this.adventureName = adventureName;
  }
}
