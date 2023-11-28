package com.dfo.dunsee.response.charequipment.enchant;

import java.util.List;
import lombok.Getter;
import lombok.ToString;

/**
 * 마법부여 관련 클래스
 * <p>
 * status: 마법부여 수치
 * <p>
 * explain: 스킬공격력 등 %증가 마법부여 수치
 */
@Getter
@ToString
public class Enchant {

  private List<Status> status;
  private String explain;
}
