package com.dfo.dunsee.apiresponse.charavatar;

import java.util.ArrayList;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
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
