package com.dfo.dunsee.apiresponse.charstatus;

import java.util.List;
import lombok.Getter;
import lombok.ToString;

/**
 * 모험단 버프 및 길드 버프 클래스
 * <p>
 * name: 버프종류 이름
 * <p>
 * level: 모험단 레벨
 * <p>
 * Status: 스탯 정보 클래스
 */
@Getter
@ToString
public class Buff {

  private String name;
  private int level;
  private List<Status> status;
}
