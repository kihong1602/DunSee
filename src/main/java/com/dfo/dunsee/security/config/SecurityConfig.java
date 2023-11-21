package com.dfo.dunsee.security.config;

import com.dfo.dunsee.security.auth.oauth.Oauth2UserService;
import com.dfo.dunsee.security.handler.exception.LoginFailureHandler;
import com.dfo.dunsee.security.handler.exception.SecurityAccessDeniedHandler;
import com.dfo.dunsee.security.handler.exception.SecurityAuthenticationEntryPoint;
import com.dfo.dunsee.security.handler.success.LoginSuccessHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@AllArgsConstructor
public class SecurityConfig {

  private final Oauth2UserService oauth2UserService;
  private final SecurityAccessDeniedHandler accessDeniedHandler;
  private final SecurityAuthenticationEntryPoint authenticationEntryPoint;
  private final LoginSuccessHandler loginSuccessHandler;
  private final LoginFailureHandler loginFailureHandler;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                       .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                           .requestMatchers("/favorite/**")
                           .authenticated()
                           .requestMatchers("/admin/**")
                           .hasRole("ADMIN")
                           .anyRequest()
                           .permitAll()
                       )
                       .exceptionHandling(handling -> handling
                           .accessDeniedHandler(accessDeniedHandler)
                           .authenticationEntryPoint(authenticationEntryPoint)
                       )
                       .oauth2Login(oauth2Login -> oauth2Login
                           .loginPage("/login")
                           .successHandler(loginSuccessHandler)
                           .failureHandler(loginFailureHandler)
                           .userInfoEndpoint(userInfo -> userInfo.userService(oauth2UserService))
                       )
                       .logout(logout -> logout
                           .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                           .logoutSuccessUrl("/")
                           .invalidateHttpSession(true)
                       )
                       .build();

  }


}
