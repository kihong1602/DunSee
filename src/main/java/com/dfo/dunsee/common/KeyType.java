package com.dfo.dunsee.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum KeyType {

  DEFAULT("default", "기본정보 URL"),
  STATUS("status", "스탯정보 URL"),
  EQUIP("equip", "장작장비정보 URL"),
  CREATURE("creature", "크리쳐정보 URL"),
  TALISMAN("talisman", "탈리스만정보 URL"),
  AVATAR("avatar", "아바타정보 URL");

  private final String key;
  private final String description;
}
