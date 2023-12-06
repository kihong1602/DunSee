package com.dfo.dunsee.security.config;

import com.dfo.dunsee.security.auth.oauth.Oauth2UserService;
import com.dfo.dunsee.security.filter.JsonUsernamePasswordAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

  private final SecurityHandlersConfig securityHandlersConfig;
  private final Oauth2UserService oauth2UserService;
  private final JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordAuthenticationFilter;


  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                       .addFilterBefore(jsonUsernamePasswordAuthenticationFilter,
                                        UsernamePasswordAuthenticationFilter.class)
                       .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                           .requestMatchers("/bookmark/**")
                           .authenticated()
                           .requestMatchers("/admin/**")
                           .hasRole("ADMIN")
                           .anyRequest()
                           .permitAll()
                       )
                       .exceptionHandling(handling -> handling
                           .accessDeniedHandler(securityHandlersConfig.getSecurityAccessDeniedHandler())
                           .authenticationEntryPoint(securityHandlersConfig.getSecurityAuthenticationEntryPoint())
                       )
                       .oauth2Login(oauth2Login -> oauth2Login
                           .loginPage("/login")
                           .successHandler(securityHandlersConfig.getLoginSuccessHandler())
                           .failureHandler(securityHandlersConfig.getJsonLoginFailureHandler())
                           .userInfoEndpoint(
                               userInfo -> userInfo.userService(oauth2UserService))
                       )
                       .logout(logout -> logout
                           .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                           .logoutSuccessUrl("/")
                           .invalidateHttpSession(true)
                           .deleteCookies("JSESSIONID")
                       )
                       .build();

  }

}
