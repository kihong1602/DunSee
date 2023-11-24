package com.dfo.dunsee.apiresponse.characterstatus;

import java.util.List;
import lombok.Data;

/**
 * 모험단 버프 및 길드 버프 클래스
 * <p>
 * name: 버프종류 이름
 * <p>
 * level: 모험단 레벨
 * <p>
 * Status: 스탯 정보 클래스
 */
@Data
public class Buff {

  private String name;
  private int level;
  private List<Status> status;
}
