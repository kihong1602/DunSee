package com.dfo.dunsee.apiresponse.charstatus;

import lombok.Getter;
import lombok.ToString;


/**
 * 스탯정보 관련 클래스
 * <p>
 * name: 스탯 이름
 * <p>
 * value: 스탯 수치
 */
@Getter
@ToString
public class Status {

  private String name;
  private int value;

}
