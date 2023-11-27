package com.dfo.dunsee.response.charavatar;

import java.util.ArrayList;
import lombok.Data;

@Data
public class Avatar {

  private String slotId;
  private String slotName;
  private String itemId;
  private String itemName;
  private String itemRarity;
  private Clone clone;
  private String optionAbility;
  private ArrayList<Emblem> emblems;
}
