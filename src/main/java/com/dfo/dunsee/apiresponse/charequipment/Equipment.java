package com.dfo.dunsee.apiresponse.charequipment;

import com.dfo.dunsee.apiresponse.charequipment.enchant.Enchant;
import com.dfo.dunsee.apiresponse.charequipment.fusionepic.BakalInfo;
import com.dfo.dunsee.apiresponse.charequipment.fusionepic.CustomOption;
import com.dfo.dunsee.apiresponse.charequipment.fusionepic.DimensionCloisterInfo;
import com.dfo.dunsee.apiresponse.charequipment.fusionepic.DuskyIslandOption;
import com.dfo.dunsee.apiresponse.charequipment.fusionepic.IspinsInfo;
import com.dfo.dunsee.apiresponse.charequipment.fusionepic.MachineRevolutionInfo;
import com.dfo.dunsee.apiresponse.charequipment.fusionepic.UpgradeInfo;
import lombok.Getter;
import lombok.ToString;

/**
 * 장착장비 클래스
 * <p>
 * slotId: 장착부위 ID
 * <p>
 * slotName: 장착부위 이름
 * <p>
 * itemId: 장착아이템 ID
 * <p>
 * itemName: 장착아이템 이름
 * <p>
 * itemTypeId: 장착아이템 타입 ID
 * <p>
 * itemTypeName: 장착아이템 타입 이름
 * <p>
 * itemAvailableLevel: 아이템 장착레벨
 * <p>
 * itemRarity: 아이템 등급
 * <p>
 * setItemId: 아이템세트 ID
 * <p>
 * setItemName: 아이템세트 이름
 * <p>
 * Skin: 무기 스킨
 * <p>
 * reinforce: 강화 증폭 개조 수치
 * <p>
 * itemGradeName: 아이템등급
 * <p>
 * enchant: 마법부여 관련 클래스
 * <p>
 * amplificationName: 증폭 능력치 이름
 * <p>
 * refine: 재련수치
 * <p>
 * bakalInfo: 바칼무기융합 클래스
 * <p>
 * upgradeInfo: 융합에픽 아이템 정보
 * <p>
 * fixedOption: 융합전 아이템 기존 옵션
 * <p>
 * engraveName: 이명각인권 정보
 * <p>
 * machineRevolutionInfo: 기계혁명 융합에픽 정보
 * <p>
 * customOption: 커스텀에픽 정보
 * <p>
 * ispinsInfo: 이스핀즈 융합에픽 정보
 * <p>
 * dimensionCloisterInfo: 차원회랑 융합에픽 정보
 * <p>
 * duskyIslandOption: 안개섬 융합에픽 정보
 */
@Getter
@ToString
public class Equipment {

  private String slotId;
  private String slotName;
  private String itemId;
  private String itemName;
  private String itemTypeId;
  private String itemType;
  private String itemTypeDetailId;
  private String itemTypeDetail;
  private int itemAvailableLevel;
  private String itemRarity;
  private Object setItemId;
  private Object setItemName;
  private Skin skin;
  private int reinforce;
  private String itemGradeName;
  private Enchant enchant;
  private String amplificationName;
  private int refine;
  private BakalInfo bakalInfo;
  private UpgradeInfo upgradeInfo;
  private FixedOption fixedOption;
  private boolean engraveName;
  private MachineRevolutionInfo machineRevolutionInfo;
  private CustomOption customOption;
  private IspinsInfo ispinsInfo;
  private DimensionCloisterInfo dimensionCloisterInfo;
  private DuskyIslandOption duskyIslandOption;
}
