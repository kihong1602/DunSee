package com.dfo.dunsee.search.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CharacterSearchKeyword {

  private String characterName;
  private String serverId;
}
