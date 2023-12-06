package com.dfo.dunsee.response.charstatus;

import com.dfo.dunsee.response.ApiResponse;
import java.util.List;
import lombok.Getter;
import lombok.ToString;

/**
 * 캐릭터 스탯정보 클래스
 * <p>
 * Buff: 모험단버프, 길드버프
 * <p>
 * Status: 스탯정보 클래스
 */
@Getter
@ToString
public class ResCharStatusInfo implements ApiResponse {

  private String characterId;
  private String characterName;
  private int level;
  private String jobId;
  private String jobGrowId;
  private String jobName;
  private String jobGrowName;
  private String adventureName;
  private String guildId;
  private String guildName;
  private List<Buff> buff;
  private List<Status> status;
}
