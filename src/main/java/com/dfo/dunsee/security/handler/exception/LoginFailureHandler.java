package com.dfo.dunsee.security.handler.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LoginFailureHandler implements AuthenticationFailureHandler {

  private final String DEFAULT_FAILURE_URL = "/login?error=true";

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

    request.setAttribute("errorMessage", errorMessage);
    request.getRequestDispatcher(DEFAULT_FAILURE_URL).forward(request, response);
  }
}
