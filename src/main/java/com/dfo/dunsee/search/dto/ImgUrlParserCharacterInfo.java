package com.dfo.dunsee.search.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ImgUrlParserCharacterInfo {

  private String characterId;
  private String serverId;

  @Builder
  private ImgUrlParserCharacterInfo(String characterId, String serverId) {
    this.characterId = characterId;
    this.serverId = serverId;
  }
}
