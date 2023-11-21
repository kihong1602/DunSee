package com.dfo.dunsee.security.auth.oauth.provider;

import static com.dfo.dunsee.common.ProviderType.KAKAO;

import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class KakaoMemberInfo implements Oauth2UserInfo {

  private final Map<String, Object> attributes;
  private final Map<String, Object> kakaoAccountAttributes;
  private final Map<String, Object> profileAttributes;


  public KakaoMemberInfo(Map<String, Object> attributes) {
    this.attributes = attributes;
    this.kakaoAccountAttributes = (Map<String, Object>) attributes.get("kakao_account");
    this.profileAttributes = (Map<String, Object>) attributes.get("profile");
  }

  @Override
  public String getProviderId() {
    return String.valueOf(attributes.get("id"));
  }

  @Override
  public String getProvider() {
    return KAKAO.getProvider();
  }

  @Override
  public String getEmail() {
    return (String) kakaoAccountAttributes.get("email");
  }

  @Override
  public String getName() {
    return (String) profileAttributes.get("nickname");
  }
}
