package com.dfo.dunsee.config;

import com.dfo.dunsee.security.exception.SecurityAccessDeniedHandler;
import com.dfo.dunsee.security.oAuth.Oauth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

  private final Oauth2UserService oauth2UserService;
  private final SecurityAccessDeniedHandler accessDeniedHandler;

  public SecurityConfig(Oauth2UserService oauth2UserService, SecurityAccessDeniedHandler accessDeniedHandler) {
    this.oauth2UserService = oauth2UserService;
    this.accessDeniedHandler = accessDeniedHandler;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                       .exceptionHandling(handling -> handling
                           .accessDeniedHandler(accessDeniedHandler))
                       .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                           .requestMatchers("/favorite/**")
                           .authenticated()
                           .requestMatchers("/admin/**")
                           .hasRole("ADMIN")
                           .anyRequest()
                           .permitAll()
                       )
                       .oauth2Login(oauth2Login -> oauth2Login
                           .loginPage("/login")
                           .userInfoEndpoint(userInfo -> userInfo.userService(oauth2UserService))
                       )
                       .build();
    
  }

}
