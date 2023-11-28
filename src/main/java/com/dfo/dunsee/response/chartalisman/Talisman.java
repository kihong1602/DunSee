package com.dfo.dunsee.response.chartalisman;

import java.util.List;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Talisman {

  private TalismanDetails talisman;
  private List<Rune> runes;

}
