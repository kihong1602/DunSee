package com.dfo.dunsee.security.filter;

import static com.dfo.dunsee.common.ServiceCode.setServiceMsg;

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
import org.springframework.security.authentication.AuthenticationManager;
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
  private static final ServiceCode SERVICE_CODE = ServiceCode.MBR201;
  private static final String DEFAULT_LOGIN_REQUEST_URL = "/login-process";
  private static final AntPathRequestMatcher DEFAULT_LOGIN_PATH_REQUEST_MATCHER = new AntPathRequestMatcher(
      DEFAULT_LOGIN_REQUEST_URL, HttpMethod.POST.name());

  public JsonUsernamePasswordAuthenticationFilter(JsonUtils jsonUtils, AuthenticationManager authenticationManager,
      AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler) {
    super(DEFAULT_LOGIN_PATH_REQUEST_MATCHER, authenticationManager);
    setAuthenticationSuccessHandler(successHandler);
    setAuthenticationFailureHandler(failureHandler);

    this.jsonUtils = jsonUtils;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException, IOException, ServletException {

    final String REQUEST_CONTENT_TYPE = request.getContentType();

    if (REQUEST_CONTENT_TYPE == null || !REQUEST_CONTENT_TYPE.equals(MediaType.APPLICATION_JSON_VALUE)) {
      throw new AuthenticationServiceException(
          setServiceMsg(SERVICE_CODE)
              + "Error:  Authentication Content-Type not supported [" + request.getContentType() + "]");
    }

    LoginDto loginDto = parseAuthRequestData(SERVICE_CODE, request, LoginDto.class);

    String username = obtainUsername(loginDto);
    String password = obtainPassword(loginDto);
    if (username == null || password == null) {
      throw new AuthenticationServiceException(setServiceMsg(SERVICE_CODE) + " Error: Data is Miss");
    }

    UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username,
                                                                                              password);
    setDetails(request, authRequest);

    return super.getAuthenticationManager()
                .authenticate(authRequest);

  }

  protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
    authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
  }

  protected String obtainUsername(LoginDto loginDto) {
    return loginDto.getUsername();
  }

  protected String obtainPassword(LoginDto loginDto) {
    return loginDto.getPassword();
  }

  protected <T extends LoginDto> T parseAuthRequestData(ServiceCode serviceCode, HttpServletRequest request,
      Class<T> classType) {
    return jsonUtils.parseJson(serviceCode, request, classType);
  }

}
