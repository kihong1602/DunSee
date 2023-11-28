package com.dfo.dunsee.response.charcreature;

import java.util.List;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Creature {

  private String itemId;
  private String itemName;
  private String itemRarity;
  private Clone clone;
  private List<Artifact> artifact;
}
