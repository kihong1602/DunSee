package com.dfo.dunsee.security.oAuth.provider;

public interface Oauth2UserInfo {

  String getProviderId();

  String getProvider();

  String getEmail();

  String getName();

}
