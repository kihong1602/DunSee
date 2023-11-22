package com.dfo.dunsee.security.handler.exception;

import com.dfo.dunsee.common.RoleType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class SecurityAccessDeniedHandler implements AccessDeniedHandler {

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException accessDeniedException) throws IOException, ServletException {
    boolean isUser = request.isUserInRole(RoleType.USER.getValue());
    boolean isManager = request.isUserInRole(RoleType.MANAGER.getValue());

    if (isUser || isManager) {
      response.sendError(HttpStatus.FORBIDDEN.value());
    } else {
      response.sendRedirect("login");
    }
  }
}
