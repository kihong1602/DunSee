package com.dfo.dunsee.search.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ImgUrlParserCharacterInfo {

  private String characterId;
  private String serverId;
}
