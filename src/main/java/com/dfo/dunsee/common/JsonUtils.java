package com.dfo.dunsee.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class JsonUtils {

  private final ObjectMapper objectMapper;

  public <T> T parseJson(ServiceCode serviceCode, HttpServletRequest request, Class<T> type) {
    try {
      String json = request.getReader()
                           .lines()
                           .collect(Collectors.joining());
      return objectMapper.readValue(json, type);
    } catch (IOException e) {
      log.error(serviceCode.name() + " | " + serviceCode.getDescription() + " :: Error", e.getCause());
      return null;
    }
  }

  public String convertObjectToJson(ServiceCode serviceCode, Object object) {
    try {
      return objectMapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      log.error(serviceCode.name() + " | " + serviceCode.getDescription() + " :: Error", e.getCause());
      return null;
    }
  }

}