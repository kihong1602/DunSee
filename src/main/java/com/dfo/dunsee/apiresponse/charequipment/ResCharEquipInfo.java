package com.dfo.dunsee.apiresponse.charequipment;

import com.dfo.dunsee.apiresponse.ApiResponse;
import java.util.List;
import lombok.Getter;
import lombok.ToString;

/**
 * 캐릭터 장착 장비 총괄 클래스
 */
@Getter
@ToString
public class ResCharEquipInfo implements ApiResponse {

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
