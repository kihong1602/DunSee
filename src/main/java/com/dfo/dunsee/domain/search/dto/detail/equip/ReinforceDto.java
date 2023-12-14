package com.dfo.dunsee.domain.search.dto.detail.equip;

public class ReinforceDto {

  private String amplificationName;
  private int reinforce;


  private ReinforceDto(String amplificationName, int reinforce) {
    this.amplificationName = amplificationName;
    this.reinforce = reinforce;
  }

  public static ReinforceDto createReinforceDto(String amplificationName, int reinforce) {
    if (amplificationName != null && amplificationName.contains("차원")) {
      amplificationName = "증폭";
    } else {
      amplificationName = "강화";
    }
    return new ReinforceDto(amplificationName, reinforce);

  }
}
