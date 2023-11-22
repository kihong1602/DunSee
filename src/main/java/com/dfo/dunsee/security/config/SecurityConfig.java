package com.dfo.dunsee.security.config;

import com.dfo.dunsee.config.CommonUtilsConfig;
import com.dfo.dunsee.security.filter.JsonUsernamePasswordAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
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
@AllArgsConstructor
public class SecurityConfig {

  private final SecurityHandlersConfig securityHandlersConfig;
  private final SecurityUserServiceConfig securityUserServiceConfig;
  private final CommonUtilsConfig commonUtilsConfig;


  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                       .addFilterBefore(jsonUsernamePasswordAuthenticationFilter(),
                                        UsernamePasswordAuthenticationFilter.class)
                       .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                           .requestMatchers("/favorite/**")
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
                       .formLogin(formLogin -> formLogin
                           .loginPage("/login")
                           .loginProcessingUrl("/login-process")
                           .successHandler(securityHandlersConfig.getLoginSuccessHandler())
                           .failureHandler(securityHandlersConfig.getLoginFailureHandler())
                       )
                       .oauth2Login(oauth2Login -> oauth2Login
                           .loginPage("/login")
                           .successHandler(securityHandlersConfig.getLoginSuccessHandler())
                           .failureHandler(securityHandlersConfig.getLoginFailureHandler())
                           .userInfoEndpoint(
                               userInfo -> userInfo.userService(securityUserServiceConfig.getOauth2UserService()))
                       )
                       .logout(logout -> logout
                           .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                           .logoutSuccessUrl("/")
                           .invalidateHttpSession(true)
                       )
                       .build();

  }

  @Bean
  public JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordAuthenticationFilter() {
    JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordAuthenticationFilter = new JsonUsernamePasswordAuthenticationFilter(
        commonUtilsConfig.getJsonUtils(), securityHandlersConfig.getLoginSuccessHandler(),
        securityHandlersConfig.getLoginFailureHandler());
    jsonUsernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManager());

    return jsonUsernamePasswordAuthenticationFilter;
  }

  @Bean
  public AuthenticationManager authenticationManager() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

    provider.setPasswordEncoder(commonUtilsConfig.getBCryptPasswordEncoder());
    provider.setUserDetailsService(securityUserServiceConfig.getPrincipalDetailsService());
    return new ProviderManager(provider);
  }
}
