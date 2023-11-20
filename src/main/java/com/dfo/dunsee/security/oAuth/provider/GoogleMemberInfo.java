package com.dfo.dunsee.security.oAuth.provider;

import static com.dfo.dunsee.common.ProviderType.GOOGLE;

import java.util.Map;

public class GoogleMemberInfo implements Oauth2UserInfo {

  private final Map<String, Object> attributes;

  public GoogleMemberInfo(Map<String, Object> attributes) {
    this.attributes = attributes;
  }

  @Override
  public String getProviderId() {
    return (String) attributes.get("sub");
  }

  @Override
  public String getProvider() {
    return GOOGLE.getProverName();
  }

  @Override
  public String getEmail() {
    return (String) attributes.get("email");
  }

  @Override
  public String getName() {
    return (String) attributes.get("name");
  }
}
