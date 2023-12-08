package com.dfo.dunsee.config;

import com.dfo.dunsee.common.JsonUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
@Getter
public class CommonUtilsConfig {

  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final JsonUtils jsonUtils;
}
