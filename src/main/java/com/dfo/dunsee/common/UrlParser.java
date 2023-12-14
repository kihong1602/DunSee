package com.dfo.dunsee.common;

import com.dfo.dunsee.domain.search.dto.ImgUrlParserCharacterInfo;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

@Component
@RequiredArgsConstructor
@Slf4j
public class UrlParser {

  private final AntPathMatcher antPathMatcher;
  private final String IMAGE_URL = "https://img-api.neople.co.kr/df/servers/{serverId}/characters/{characterId}?zoom=2";

  public ImgUrlParserCharacterInfo imgUrlParseCharacterInfo(ServiceCode serviceCode, String imgUrl) {
    log.info(ServiceCode.setServiceMsg(serviceCode) + "ImageUrl Parsing Process");
    Map<String, String> parseImgUrlMap = antPathMatcher.extractUriTemplateVariables(IMAGE_URL, imgUrl);

    String characterId = parseImgUrlMap.get("characterId");
    String serverId = parseImgUrlMap.get("serverId");

    return ImgUrlParserCharacterInfo.builder()
        .characterId(characterId)
        .serverId(serverId)
        .build();
  }
}


