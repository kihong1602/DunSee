package com.dfo.dunsee.search;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;

import com.dfo.dunsee.common.ServiceCode;
import com.dfo.dunsee.common.UrlParser;
import com.dfo.dunsee.search.dto.ImgUrlParserCharacterInfo;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.AntPathMatcher;

@ExtendWith(MockitoExtension.class)
class UrlParserTest {

  @Mock
  private AntPathMatcher antPathMatcher;
  @InjectMocks
  private UrlParser urlParser;
  private ServiceCode serviceCode;

  @BeforeEach
  void init() {
    serviceCode = ServiceCode.CHR201;
  }


  @Test
  @DisplayName("UrlParser 테스트")
  void test1() {
    //given
    Map<String, String> mockUriVariables = new HashMap<>();
    mockUriVariables.put("characterId", "12345");
    mockUriVariables.put("serverId", "hilder");
    Mockito.when(antPathMatcher.extractUriTemplateVariables(anyString(), anyString()))
           .thenReturn(mockUriVariables);

    //when
    String requestImgUrl = "https://img-api.neople.co.kr/df/servers/hilder/characters/12345?zoom=2";
    ImgUrlParserCharacterInfo result = urlParser.imgUrlParseCharacterInfo(serviceCode,
                                                                          requestImgUrl);
    //then
    assertThat(result).isNotNull().extracting("characterId", "serverId").contains("12345", "hilder");

  }

}
