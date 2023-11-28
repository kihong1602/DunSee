package com.dfo.dunsee.response.chartalisman;

import com.dfo.dunsee.response.ApiResponse;
import java.util.List;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ResponseCharacterTalismanInfo implements ApiResponse {

  private String characterId;
  private String characterName;
  private int level;
  private String jobId;
  private String jobGrowId;
  private String jobName;
  private String jobGrowName;
  private String adventureName;
  private String guildId;
  private String guildName;
  private List<Talisman> talismans;
}
