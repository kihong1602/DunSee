package com.dfo.dunsee.security.config;

import com.dfo.dunsee.security.handler.exception.JsonLoginFailureHandler;
import com.dfo.dunsee.security.handler.exception.SecurityAccessDeniedHandler;
import com.dfo.dunsee.security.handler.exception.SecurityAuthenticationEntryPoint;
import com.dfo.dunsee.security.handler.success.LoginSuccessHandler;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Getter
public class SecurityHandlersConfig {

  private final SecurityAuthenticationEntryPoint securityAuthenticationEntryPoint;
  private final SecurityAccessDeniedHandler securityAccessDeniedHandler;
  private final LoginSuccessHandler loginSuccessHandler;
  private final JsonLoginFailureHandler jsonLoginFailureHandler;
}
