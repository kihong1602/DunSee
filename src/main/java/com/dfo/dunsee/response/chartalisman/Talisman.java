package com.dfo.dunsee.response.chartalisman;

import java.util.List;
import lombok.Data;

@Data
public class Talisman {

  private TalismanDetails talisman;
  private List<Rune> runes;

}
