package com.dfo.dunsee.search.dto.detail.talisman;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RuneDto {


  private String imgUrl;
  private String itemName;

  @Builder
  private RuneDto(String imgUrl, String itemName) {
    this.imgUrl = imgUrl;
    this.itemName = itemName;

  }
}
