package com.dfo.dunsee.domain.search.dto.detail.word;

public enum EquipSlotType {
  WEAPON("무기"),
  TITLE("칭호"),
  JACKET("상의"),
  PANTS("하의"),
  SHOULDER("머리어깨"),
  WAIST("허리"),
  SHOES("신발"),
  AMULET("목걸이"),
  WRIST("팔찌"),
  RING("반지"),
  SUPPORT("보조장비"),
  MAGIC_STON("마법석"),
  EARRING("귀걸이");

  private final String slotType;

  EquipSlotType(String slotType) {
    this.slotType = slotType;
  }
}
