package com.dfo.dunsee.apiresponse.characterequipment;

import com.dfo.dunsee.apiresponse.ApiResponse;
import java.util.List;
import lombok.Data;

/**
 * 캐릭터 장착 장비 총괄 클래스
 */
@Data
public class ResponseCharacterEquipInfo implements ApiResponse {

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
  private List<Equipment> equipment;
  private List<Object> setItemInfo;
}
