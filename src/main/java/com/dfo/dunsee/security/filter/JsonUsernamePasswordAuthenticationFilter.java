package com.dfo.dunsee.security.filter;

import com.dfo.dunsee.common.JsonUtils;
import com.dfo.dunsee.common.ServiceCode;
import com.dfo.dunsee.member.dto.LoginDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Slf4j
public class JsonUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

  private final JsonUtils jsonUtils;
  private static final String DEFAULT_LOGIN_REQUEST_URL = "/login-process";
  private static final AntPathRequestMatcher DEFAULT_LOGIN_PATH_REQUEST_MATCHER = new AntPathRequestMatcher(
      DEFAULT_LOGIN_REQUEST_URL, HttpMethod.POST.name());
  private final ServiceCode serviceCode = ServiceCode.MBR201;
  private final String serviceCodeMsg = serviceCode.name() + " | " + serviceCode.getDescription();

  public JsonUsernamePasswordAuthenticationFilter(JsonUtils jsonUtils,
      AuthenticationSuccessHandler authenticationSuccessHandler,
      AuthenticationFailureHandler authenticationFailureHandler) {
    super(DEFAULT_LOGIN_PATH_REQUEST_MATCHER);
    this.jsonUtils = jsonUtils;
    setAuthenticationSuccessHandler(authenticationSuccessHandler);
    setAuthenticationFailureHandler(authenticationFailureHandler);
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException, IOException, ServletException {
    if (request.getContentType() == null || !request.getContentType()
                                                    .equals(MediaType.APPLICATION_JSON_VALUE)) {
      throw new AuthenticationServiceException(
          serviceCodeMsg + " Error:  Authentication Content-Type not supported [" + request.getContentType()
              + "]");
    }
    LoginDto loginDto = jsonUtils.parseJson(serviceCode, request, LoginDto.class);

    String username = loginDto.getUsername();
    String password = loginDto.getPassword();
    if (username == null || password == null) {
      throw new AuthenticationServiceException(serviceCodeMsg + " Error: Data is Miss");
    }

    UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
    setDetails(request, authRequest);
    return this.getAuthenticationManager()
               .authenticate(authRequest);

  }

  protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
    authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
  }
}
