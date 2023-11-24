package com.dfo.dunsee.apiresponse.characterequipment.enchant;

import java.util.List;
import lombok.Data;

/**
 * 마법부여 관련 클래스
 * <p>
 * status: 마법부여 수치
 * <p>
 * explain: 스킬공격력 등 %증가 마법부여 수치
 */
@Data
public class Enchant {

  private List<Status> status;
  private String explain;
}
