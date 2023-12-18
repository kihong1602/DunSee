package com.dfo.dunsee.domain.search.dto;

import jakarta.validation.constraints.NotBlank;
import java.beans.ConstructorProperties;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CharacterSearchKeyword {

  @NotBlank
  private String characterName;

  private String serverId;

  @Builder
  @ConstructorProperties({"characterName", "serverId"})
  private CharacterSearchKeyword(String characterName, String serverId) {
    this.characterName = characterName;
    this.serverId = serverId;
  }
}
