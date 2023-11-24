package com.dfo.dunsee.apiresponse.characterequipment.fusionepic;

import lombok.Data;

/**
 * 아이템 옵션 Class
 * <p>
 * buff: 아이템 버프력
 * <p>
 * explain: 옵션 설명
 * <p>
 * explainDetail: 옵션 상세설명
 * <p>
 * damage: 아이템 피해증가
 * <p>
 * transfer: 커스텀 옵션변환 여부? 재질변환?
 */
@Data
public class Option {

  private int buff;
  private String explain;
  private String explainDetail;
  private int damage;
  private boolean transfer;
}
