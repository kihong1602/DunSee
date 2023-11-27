package com.dfo.dunsee.response.charcreature;

import lombok.Data;

@Data
public class Artifact {

  private String slotColor;
  private String itemId;
  private String itemName;
  private int itemAvailableLevel;
  private String itemRarity;

}
