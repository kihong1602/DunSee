package com.dfo.dunsee.search.dto;

import com.dfo.dunsee.common.Server;
import java.util.Comparator;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DefaultCharacterInfo implements Comparable<DefaultCharacterInfo> {

  private Server serverId;
  private String characterId;
  private String characterName;
  private int level;
  private String jobGrowName;
  private Integer fame;
  private String imgUrl;

  @Override
  public int compareTo(DefaultCharacterInfo other) {
    return Comparator.comparing(DefaultCharacterInfo::getFame, Comparator.nullsLast(Comparator.reverseOrder()))
                     .compare(this, other);
  }
}
