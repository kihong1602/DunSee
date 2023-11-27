package com.dfo.dunsee.common.url;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ApiUri {
  SERVERS("servers"),
  CHARACTERS("characters"),
  CHARACTER_NAME("characterName"),
  STATUS("status"),
  EQUIP("equip"),
  EQUIPMENT("equipment"),
  AVATAR("avatar"),
  CREATURE("creature"),
  TALISMAN("talisman");

  private final String value;

  public String value() {
    return value;
  }
}
