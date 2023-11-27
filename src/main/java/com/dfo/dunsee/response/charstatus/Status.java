package com.dfo.dunsee.response.charstatus;

import lombok.Data;


/**
 * 스탯정보 관련 클래스
 * <p>
 * name: 스탯 이름
 * <p>
 * value: 스탯 수치
 */
@Data
public class Status {

  private String name;
  private int value;

}
