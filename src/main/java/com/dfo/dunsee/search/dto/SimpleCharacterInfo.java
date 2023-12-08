package com.dfo.dunsee.search.dto;

import com.dfo.dunsee.apiresponse.charlist.ResCharInfo;
import com.dfo.dunsee.apiresponse.charstatus.ResCharStatusInfo;
import com.dfo.dunsee.common.Server;
import com.dfo.dunsee.member.entity.CharacterInfo;
import java.util.Comparator;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SimpleCharacterInfo implements Comparable<SimpleCharacterInfo> {

  private final Server serverId;
  private final String characterId;
  private final String characterName;
  private final int level;
  private final String jobGrowName;
  private final Integer fame;
  private final String imgUrl;

  @Builder
  private SimpleCharacterInfo(String serverId, String characterId, String characterName, int level, String jobGrowName,
      Integer fame, String imgUrl) {
    this.serverId = Server.fromValue(serverId);
    this.characterId = characterId;
    this.characterName = characterName;
    this.level = level;
    this.jobGrowName = jobGrowName;
    this.fame = fame;
    this.imgUrl = imgUrl;
  }

  public static SimpleCharacterInfo createSimpleCharacterInfo(ImgUrlParserCharacterInfo parseInfo,
      ResCharStatusInfo statusInfo, CharacterInfo characterInfo) {
    return SimpleCharacterInfo.builder()
        .serverId(parseInfo.getServerId())
        .characterId(parseInfo.getCharacterId())
        .characterName(statusInfo.getCharacterName())
        .level(statusInfo.getLevel())
        .jobGrowName(statusInfo.getJobGrowName())
        .fame(Integer.valueOf(characterInfo.getFame()))
        .imgUrl(characterInfo.getImgUrl())
        .build();
  }

  public static SimpleCharacterInfo createSimpleCharacterInfo(String imgUrl, ResCharInfo info) {
    return SimpleCharacterInfo
        .builder()
        .serverId(info.getServerId())
        .characterId(info.getCharacterId())
        .characterName(info.getCharacterName())
        .level(info.getLevel())
        .jobGrowName(info.getJobGrowName())
        .fame(info.getFame())
        .imgUrl(imgUrl)
        .build();
  }

  @Override
  public int compareTo(SimpleCharacterInfo other) {
    return Comparator.comparing(SimpleCharacterInfo::getFame, Comparator.nullsLast(Comparator.reverseOrder()))
                     .compare(this, other);
  }
}
