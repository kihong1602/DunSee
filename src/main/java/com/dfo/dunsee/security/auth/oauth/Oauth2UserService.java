package com.dfo.dunsee.security.auth.oauth;

import static com.dfo.dunsee.common.ServiceCode.setServiceMsg;

import com.dfo.dunsee.common.RoleType;
import com.dfo.dunsee.common.ServiceCode;
import com.dfo.dunsee.member.entity.Member;
import com.dfo.dunsee.member.repository.MemberRepository;
import com.dfo.dunsee.security.auth.oauth.provider.GoogleMemberInfo;
import com.dfo.dunsee.security.auth.oauth.provider.KakaoMemberInfo;
import com.dfo.dunsee.security.auth.oauth.provider.Oauth2UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class Oauth2UserService extends DefaultOAuth2UserService {

  private final MemberRepository memberRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private static final ServiceCode SERVICE_CODE = ServiceCode.MBR202;

  @Value("${dnf.password}")
  private String oAuth2Password;


  @Override
  @Transactional
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    log.info(setServiceMsg(SERVICE_CODE) + "OAuth2.0 로그인 DB 회원 조회");
    OAuth2User oAuth2User = super.loadUser(userRequest);
    String registrationId = userRequest.getClientRegistration()
                                       .getRegistrationId();

    Oauth2UserInfo oauth2UserInfo;
    switch (registrationId) {
      case "google" -> oauth2UserInfo = new GoogleMemberInfo(oAuth2User.getAttributes());
      case "kakao" -> oauth2UserInfo = new KakaoMemberInfo(oAuth2User.getAttributes());
      default ->
          throw new OAuth2AuthenticationException(setServiceMsg(SERVICE_CODE) + "OAuth2 Authentication Exception");
    }

    Member memberEntity = memberRepository.findByUsername(
        createUserName(oauth2UserInfo.getProvider(), oauth2UserInfo.getProviderId()));

    if (memberEntity == null) {
      log.info(setServiceMsg(SERVICE_CODE) + "OAuth2.0 로그인 회원가입");
      memberEntity = createOAuth2Member(oauth2UserInfo);
      memberRepository.save(memberEntity);
    } else {
      log.info(setServiceMsg(SERVICE_CODE) + "Already Join User");
    }

    return new PrincipalDetails(memberEntity, oAuth2User.getAttributes());
  }

  private String createUserName(String provider, String providerId) {
    return provider + "_" + providerId;
  }

  private Member createOAuth2Member(Oauth2UserInfo oauth2UserInfo) {
    String provider = oauth2UserInfo.getProvider();
    String providerId = oauth2UserInfo.getProviderId();
    String email = oauth2UserInfo.getEmail();
    String username = createUserName(provider, providerId);
    String password = bCryptPasswordEncoder.encode(oAuth2Password);
    String role = RoleType.USER.getValue();

    return Member.builder()
        .username(username)
        .password(password)
        .email(email)
        .role(role)
        .provider(provider)
        .providerId(providerId)
        .build();
  }
}
