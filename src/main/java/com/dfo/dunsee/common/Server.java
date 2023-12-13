package com.dfo.dunsee.common;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Server {
  ALL("all", "전체"),
  ADVENTURE("adventure", "모험단"),
  CAIN("cain", "카인"),
  DIREGIE("diregie", "디레지에"),
  SIROCO("siroco", "시로코"),
  PREY("prey", "프레이"),
  CASILLAS("casillas", "카시야스"),
  HILDER("hilder", "힐더"),
  ANTON("anton", "안톤"),
  BAKAL("bakal", "바칼");

  private final String engName;
  private final String korName;


  public String getEngName() {
    return engName;
  }

  @JsonValue
  public String getKorName() {
    return korName;
  }

  public static Server fromValue(String value) {
    for (Server server : Server.values()) {
      if (server.engName.equalsIgnoreCase(value) || server.korName.equalsIgnoreCase(value)) {
        return server;
      }
    }
    throw new IllegalArgumentException("Unknown ServerId: " + value);
  }

}
