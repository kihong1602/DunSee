package com.dfo.dunsee.response.charequipment;

import lombok.Getter;
import lombok.ToString;

/**
 * 융합에픽 내부에 있는 기존 아이템 옵션
 * <p>
 * damage: 아이템 피해증가
 * <p>
 * buff: 아이템 버프력
 * <p>
 * level: 아이템 레벨
 * <p>
 * expRate: 아이템 경험치
 * <p>
 * explain: 아이템 설명
 * <p>
 * explainDetail: 아이템 상세설명
 */
@Getter
@ToString
public class FixedOption {

  private int damage;
  private int buff;
  private int level;
  private double expRate;
  private String explain;
  private String explainDetail;

}
