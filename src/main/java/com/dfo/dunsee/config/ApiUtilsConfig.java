package com.dfo.dunsee.config;

import com.dfo.dunsee.common.ApiUtils;
import com.dfo.dunsee.common.UrlFactory;
import com.dfo.dunsee.common.UrlParser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Getter
public class ApiUtilsConfig {

  private final ApiUtils apiUtils;
  private final UrlFactory urlFactory;
  private final UrlParser urlParser;
}
