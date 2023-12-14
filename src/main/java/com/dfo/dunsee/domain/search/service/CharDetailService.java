package com.dfo.dunsee.domain.search.service;

import static com.dfo.dunsee.common.KeyType.AVATAR;
import static com.dfo.dunsee.common.KeyType.CREATURE;
import static com.dfo.dunsee.common.KeyType.DEFAULT;
import static com.dfo.dunsee.common.KeyType.EQUIP;
import static com.dfo.dunsee.common.KeyType.STATUS;
import static com.dfo.dunsee.common.KeyType.TALISMAN;

import com.dfo.dunsee.apiresponse.ApiResponse;
import com.dfo.dunsee.apiresponse.charavatar.ResCharAvatarInfo;
import com.dfo.dunsee.apiresponse.charcreature.ResCreatureInfo;
import com.dfo.dunsee.apiresponse.chardefault.ResCharDefaultInfo;
import com.dfo.dunsee.apiresponse.charequipment.ResCharEquipInfo;
import com.dfo.dunsee.apiresponse.charstatus.ResCharStatusInfo;
import com.dfo.dunsee.apiresponse.chartalisman.ResCharTalismanInfo;
import com.dfo.dunsee.common.KeyType;
import com.dfo.dunsee.common.ServiceCode;
import com.dfo.dunsee.config.ApiUtilsConfig;
import com.dfo.dunsee.domain.member.entity.CharacterInfo;
import com.dfo.dunsee.domain.search.dto.ImgUrlParserCharacterInfo;
import com.dfo.dunsee.domain.search.dto.detail.DetailCharInfoDto;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CharDetailService {

  private final ApiUtilsConfig apiUtilsConfig;
  private final CharDataProcessor charDataProcessor;
  private final CharSaveService charSaveService;

  private String serviceMsg;

  public DetailCharInfoDto charDetailSearch(ServiceCode serviceCode, String imgUrl) {
    serviceMsg = ServiceCode.setServiceMsg(serviceCode);
    log.info(serviceMsg + "Sync Character Detail Info Search Service Start");

    ImgUrlParserCharacterInfo imgUrlParserCharacterInfo = apiUtilsConfig.getUrlParser()
                                                                        .imgUrlParseCharacterInfo(serviceCode, imgUrl);

    Map<KeyType, String> urlMap = setCallApiUrlMap(serviceCode, imgUrlParserCharacterInfo.getServerId(),
                                                   imgUrlParserCharacterInfo.getCharacterId());

    Map<KeyType, ApiResponse> resMap = getApiResponseData(serviceCode, urlMap);

    DetailCharInfoDto detailCharInfoDto = buildCharacterDetails(resMap, imgUrl);

    CompletableFuture.runAsync(() -> saveCharacterInfoProcess(detailCharInfoDto));
    return detailCharInfoDto;
  }


  private void saveCharacterInfoProcess(DetailCharInfoDto detailCharInfoDto) {
    log.info(serviceMsg + "Async Character Info Save Process Start");
    CharacterInfo characterInfo = CharacterInfo.createCharacterInfo(detailCharInfoDto);

    boolean result = charSaveService.saveCharacterInfo(characterInfo);
    if (result) {
      log.info(serviceMsg + "Character Info Save Success");
    } else {
      log.error(serviceMsg + "Character Info Save Fail");
    }
  }

  private Map<KeyType, String> setCallApiUrlMap(ServiceCode serviceCode, String serverId, String characterId) {
    return Map.of(
        DEFAULT, apiUtilsConfig.getUrlFactory()
                               .setSearchCharacterDefaultUrl(serviceCode, serverId, characterId),
        STATUS, apiUtilsConfig.getUrlFactory()
                              .setSearchCharacterStatusUrl(serviceCode, serverId, characterId),
        EQUIP, apiUtilsConfig.getUrlFactory()
                             .setSearchCharacterEquipUrl(serviceCode, serverId, characterId),
        CREATURE, apiUtilsConfig.getUrlFactory()
                                .setSearchCreatureUrl(serviceCode, serverId, characterId),
        AVATAR, apiUtilsConfig.getUrlFactory()
                              .setSearchCharacterAvatarUrl(serviceCode, serverId, characterId),
        TALISMAN, apiUtilsConfig.getUrlFactory()
                                .setSearchTalismanUrl(serviceCode, serverId, characterId)
    );
  }

  private Map<KeyType, ApiResponse> getApiResponseData(ServiceCode serviceCode, Map<KeyType, String> urlMap) {
    Map<KeyType, Class<? extends ApiResponse>> typeClassMap = createTypeClassMap();
    List<KeyType> keyTypes = Arrays.asList(KeyType.values());

    List<CompletableFuture<ApiResponse>> apiCalls =
        keyTypes.stream()
                .map(keyType -> apiUtilsConfig.getApiUtils().callNeopleApi(serviceCode, urlMap.get(keyType),
                                                                           typeClassMap.get(keyType))).toList();

    return apiCalls.stream()
                   .collect(Collectors
                                .toMap(apiCall -> keyTypes.get(apiCalls.indexOf(apiCall)), CompletableFuture::join));
  }

  private Map<KeyType, Class<? extends ApiResponse>> createTypeClassMap() {
    return Map.of(DEFAULT, ResCharDefaultInfo.class,
                  STATUS, ResCharStatusInfo.class,
                  EQUIP, ResCharEquipInfo.class,
                  CREATURE, ResCreatureInfo.class,
                  AVATAR, ResCharAvatarInfo.class,
                  TALISMAN, ResCharTalismanInfo.class);
  }

  private DetailCharInfoDto buildCharacterDetails(Map<KeyType, ApiResponse> resMap, String imgUrl) {

    return charDataProcessor.buildCharacterDetails(resMap, imgUrl);
  }

}
