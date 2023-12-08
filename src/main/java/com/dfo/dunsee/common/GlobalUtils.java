package com.dfo.dunsee.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.AntPathMatcher;

@Configuration
public class GlobalUtils {

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }

  @Bean
  public JsonUtils jsonUtils(ObjectMapper objectMapper) {
    return new JsonUtils(objectMapper);
  }

  @Bean
  public AntPathMatcher antPathMatcher() {
    return new AntPathMatcher();
  }
}
