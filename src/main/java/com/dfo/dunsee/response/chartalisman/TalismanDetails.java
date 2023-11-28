package com.dfo.dunsee.response.chartalisman;

import java.util.List;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TalismanDetails {

  private int slotNo;
  private String itemId;
  private String itemName;
  private List<String> runeTypes;
}
