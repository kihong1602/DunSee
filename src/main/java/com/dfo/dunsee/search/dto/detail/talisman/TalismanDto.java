package com.dfo.dunsee.search.dto.detail.talisman;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TalismanDto {

  private String imgUrl;
  private String itemName;

  private List<RuneDto> runeDtos;

  @Builder
  private TalismanDto(String imgUrl, String itemName, List<RuneDto> runeDtos) {
    this.imgUrl = imgUrl;
    this.itemName = itemName;
    this.runeDtos = runeDtos;
  }
}
