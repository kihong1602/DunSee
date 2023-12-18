package com.dfo.dunsee.domain.search.dto;

import java.beans.ConstructorProperties;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CharacterSearchKeyword {

  private String characterName;
  private String serverId;

  @Builder
  @ConstructorProperties({"characterName", "serverId"})
  private CharacterSearchKeyword(String characterName, String serverId) {
    this.characterName = characterName;
    this.serverId = serverId;
  }
}
