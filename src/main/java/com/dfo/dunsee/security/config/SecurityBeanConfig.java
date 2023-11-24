package com.dfo.dunsee.security.config;

import com.dfo.dunsee.common.JsonUtils;
import com.dfo.dunsee.security.auth.oauth.PrincipalDetailsService;
import com.dfo.dunsee.security.filter.JsonUsernamePasswordAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@RequiredArgsConstructor
public class SecurityBeanConfig {

  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final PrincipalDetailsService principalDetailsService;

  @Bean
  public AuthenticationManager authenticationManager() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(bCryptPasswordEncoder);
    provider.setUserDetailsService(principalDetailsService);

    return new ProviderManager(provider);
  }

  @Bean
  JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordAuthenticationFilter(JsonUtils jsonUtils,
      AuthenticationManager authenticationManager, AuthenticationSuccessHandler successHandler,
      AuthenticationFailureHandler failureHandler) {

    return new JsonUsernamePasswordAuthenticationFilter(
        jsonUtils, authenticationManager, successHandler, failureHandler);
  }
}
