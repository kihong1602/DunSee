package com.dfo.dunsee.security.config;

import com.dfo.dunsee.security.auth.oauth.Oauth2UserService;
import com.dfo.dunsee.security.auth.oauth.PrincipalDetailsService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
@Getter
public class SecurityUserServiceConfig {

  private final PrincipalDetailsService principalDetailsService;
  private final Oauth2UserService oauth2UserService;

}
