package com.dfo.dunsee.domain.search.dto.detail.word;

import lombok.Getter;

@Getter
public enum StatusType {
  FAME("모험가 명성"),
  STR("힘"),
  INT("지능"),
  VIT("체력"),
  SPR("정신력"),
  PATK("물리 공격"),
  MATK("마법 공격"),
  IATK("독립 공격"),
  PDEF("물리 방어율"),
  MDEF("마법 방어율"),
  ASPD("공격 속도"),
  CAST("캐스팅 속도"),
  MSPEED("이동 속도"),
  FENH("화속성 강화"),
  CENH("수속성 강화"),
  LENH("명속성 강화"),
  DENH("암속성 강화"),
  FRES("화속성 저항"),
  CRES("수속성 저항"),
  LRES("명속성 저항"),
  DRES("암속성 저항");
  private final String name;

  StatusType(String name) {
    this.name = name;
  }
}
