package com.dfo.dunsee.common;

import com.dfo.dunsee.common.response.ResponseJson;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

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

  public void sendResponseJson(ServiceCode serviceCode, HttpServletResponse response, ResponseJson responseJson)
      throws IOException {

    String json = convertObjectToJson(serviceCode, responseJson);
    response.setStatus(HttpStatus.OK.value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.getWriter()
            .write(json);
  }
}
