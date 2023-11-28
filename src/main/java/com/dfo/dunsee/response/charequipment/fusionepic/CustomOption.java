package com.dfo.dunsee.response.charequipment.fusionepic;

import java.util.List;
import lombok.Getter;
import lombok.ToString;

/**
 * 커스텀 에픽 관련 클래스
 * <p>
 * damage: 커스텀에픽 피해증가
 * <p>
 * buff: 커스텀에픽 버프력
 * <p>
 * expRate: 아이템 경험치
 */
@Getter
@ToString
public class CustomOption {

  private int damage;
  private int buff;
  private int level;
  private double expRate;
  private List<Option> options;
}
