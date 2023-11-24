package com.dfo.dunsee.security.handler.exception;

import static com.dfo.dunsee.common.ResultType.FAILURE;

import com.dfo.dunsee.common.JsonUtils;
import com.dfo.dunsee.common.ServiceCode;
import com.dfo.dunsee.common.response.ResponseJson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JsonLoginFailureHandler implements AuthenticationFailureHandler {

  private final JsonUtils jsonUtils;
  private static final ServiceCode SERVICE_CODE = ServiceCode.MBR201;

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException exception) throws IOException, ServletException {
    String errorMessage;

    if (exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException) {
      errorMessage = "아이디나 비밀번호가 맞지 않습니다. 다시 확인해주세요.";
    } else if (exception instanceof DisabledException) {
      errorMessage = "계정이 비활성화 되었습니다. 관리자에게 문의하세요.";
    } else if (exception instanceof CredentialsExpiredException) {
      errorMessage = "비밀번호 유효기간이 만료되었습니다. 관리자에게 문의하세요.";
    } else {
      errorMessage = "알수없은 오류로 로그인에 실패하였습니다. 관리자에게 문의하세요.";
    }

    ResponseJson responseJson = ResponseJson.setResponseJson(SERVICE_CODE, FAILURE, errorMessage);

    jsonUtils.sendResponseJson(SERVICE_CODE, response, responseJson);
  }
}
