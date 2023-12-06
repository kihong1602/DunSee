package com.dfo.dunsee.search.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CharacterSearchKeyword {

  private String characterName;
  private String serverId;

  @Builder
  private CharacterSearchKeyword(String characterName, String serverId) {
    this.characterName = characterName;
    this.serverId = serverId;
  }
}
