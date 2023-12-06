package com.dfo.dunsee.response.charavatar;

import com.dfo.dunsee.response.ApiResponse;
import java.util.ArrayList;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ResCharAvatarInfo implements ApiResponse {

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
  private ArrayList<Avatar> avatar;
}
