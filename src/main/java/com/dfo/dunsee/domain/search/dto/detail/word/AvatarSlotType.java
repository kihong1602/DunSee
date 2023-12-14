package com.dfo.dunsee.domain.search.dto.detail.word;

public enum AvatarSlotType {
  HEADGEAR("모자 아바타"),
  HAIR("머리 아바타"),
  FACE("얼굴 아바타"),
  JACKET("상의 아바타"),
  PANTS("하의 아바타"),
  SHOES("신발 아바타"),
  WAIST("허리 아바타"),
  BREAST("목가슴 아바타"),
  SKIN("스킨 아바타"),
  AURORA("오라 아바타"),
  WEAPON("무기 아바타");


  private final String slotType;

  AvatarSlotType(String slotType) {
    this.slotType = slotType;
  }
}
