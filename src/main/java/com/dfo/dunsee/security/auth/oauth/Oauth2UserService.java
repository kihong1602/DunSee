package com.dfo.dunsee.security.auth.oauth;

import com.dfo.dunsee.common.RoleType;
import com.dfo.dunsee.member.entity.Member;
import com.dfo.dunsee.member.repository.MemberRepository;
import com.dfo.dunsee.security.auth.oauth.provider.GoogleMemberInfo;
import com.dfo.dunsee.security.auth.oauth.provider.KakaoMemberInfo;
import com.dfo.dunsee.security.auth.oauth.provider.Oauth2UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Oauth2UserService extends DefaultOAuth2UserService {

  private final MemberRepository memberRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public Oauth2UserService(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.memberRepository = memberRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

    OAuth2User oAuth2User = super.loadUser(userRequest);
    String registrationId = userRequest.getClientRegistration()
                                       .getRegistrationId();

    Oauth2UserInfo oauth2UserInfo = null;
    switch (registrationId) {
      case "google" -> oauth2UserInfo = new GoogleMemberInfo(oAuth2User.getAttributes());
      case "kakao" -> oauth2UserInfo = new KakaoMemberInfo(oAuth2User.getAttributes());
    }

    String provider = oauth2UserInfo.getProvider();
    String providerId = oauth2UserInfo.getProviderId();
    String email = oauth2UserInfo.getEmail();
    String username = createUserName(provider, providerId);
    String password = bCryptPasswordEncoder.encode("OAuth2SignUp");
    String role = RoleType.USER.getValue();

    Member memberEntity = memberRepository.findByUsername(username);
    if (memberEntity == null) {
      memberEntity = Member.builder()
                           .username(username)
                           .password(password)
                           .email(email)
                           .role(role)
                           .provider(provider)
                           .providerId(providerId)
                           .build();
      memberRepository.save(memberEntity);
    }

    return new PrincipalDetails(memberEntity, oAuth2User.getAttributes());
  }

  private String createUserName(String provider, String providerId) {
    return provider + "_" + providerId;
  }
}
