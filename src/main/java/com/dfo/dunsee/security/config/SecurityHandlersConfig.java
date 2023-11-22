package com.dfo.dunsee.security.config;

import com.dfo.dunsee.security.handler.exception.LoginFailureHandler;
import com.dfo.dunsee.security.handler.exception.SecurityAccessDeniedHandler;
import com.dfo.dunsee.security.handler.exception.SecurityAuthenticationEntryPoint;
import com.dfo.dunsee.security.handler.success.LoginSuccessHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
@Getter
public class SecurityHandlersConfig {

  private final SecurityAccessDeniedHandler securityAccessDeniedHandler;
  private final SecurityAuthenticationEntryPoint securityAuthenticationEntryPoint;
  private final LoginSuccessHandler loginSuccessHandler;
  private final LoginFailureHandler loginFailureHandler;
}
