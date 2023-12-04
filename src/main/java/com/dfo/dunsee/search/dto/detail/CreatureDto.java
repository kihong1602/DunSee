package com.dfo.dunsee.search.dto.detail;

import lombok.Builder;

public class CreatureDto {

  private String imgUrl;
  private String itemName;

  @Builder
  private CreatureDto(String imgUrl, String itemName) {
    this.imgUrl = imgUrl;
    this.itemName = itemName;
  }
}
