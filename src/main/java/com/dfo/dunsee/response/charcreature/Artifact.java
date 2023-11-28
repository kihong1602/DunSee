package com.dfo.dunsee.response.charcreature;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Artifact {

  private String slotColor;
  private String itemId;
  private String itemName;
  private int itemAvailableLevel;
  private String itemRarity;

}
