package com.dfo.dunsee.domain.search.dto.detail;

import com.dfo.dunsee.apiresponse.chardefault.ResCharDefaultInfo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class NormalCharInfoDto {

  private String characterName;
  private int level;
  private String jobGrowName;
  private String guildName;
  private String adventureName;

  @Builder
  private NormalCharInfoDto(String characterName, int level, String jobGrowName, String guildName,
      String adventureName) {
    this.characterName = characterName;
    this.level = level;
    this.jobGrowName = jobGrowName;
    this.guildName = guildName;
    this.adventureName = adventureName;
  }

  public static NormalCharInfoDto createNormalCharInfoDto(ResCharDefaultInfo defaultInfo) {
    String characterName = defaultInfo.getCharacterName();
    int level = defaultInfo.getLevel();
    String jobGrowName = defaultInfo.getJobGrowName();
    String guildName = defaultInfo.getGuildName();
    String adventureName = defaultInfo.getAdventureName();

    return NormalCharInfoDto.builder()
        .characterName(characterName)
        .level(level)
        .jobGrowName(jobGrowName)
        .guildName(guildName)
        .adventureName(adventureName)
        .build();
  }
}
