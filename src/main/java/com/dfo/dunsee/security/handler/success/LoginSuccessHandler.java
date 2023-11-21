package com.dfo.dunsee.security.handler.success;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

  private final String DEFAULT_URL = "/";
  private final RequestCache requestCache = new HttpSessionRequestCache();
  private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {

    resultRedirectStrategy(request, response);
  }

  protected void resultRedirectStrategy(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    SavedRequest savedRequest = requestCache.getRequest(request, response);

    if (savedRequest != null) {
      String targetUrl = savedRequest.getRedirectUrl();
      redirectStrategy.sendRedirect(request, response, targetUrl);
    } else {
      redirectStrategy.sendRedirect(request, response, DEFAULT_URL);
    }
  }
}
