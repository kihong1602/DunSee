package com.dfo.dunsee.config;

import com.dfo.dunsee.common.JsonUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@AllArgsConstructor
@Getter
public class CommonUtilsConfig {

  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final JsonUtils jsonUtils;
}
