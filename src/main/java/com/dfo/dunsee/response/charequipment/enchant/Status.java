package com.dfo.dunsee.response.charequipment.enchant;

import lombok.Getter;
import lombok.ToString;

/**
 * 아이템 마법부여 수치 관련 클래스
 * <p>
 * name: 마법부여 능력치 이름
 * <p>
 * value: 마법부여 능력치 수치
 */
@Getter
@ToString
public class Status {

  private String name;
  private String value;

}
