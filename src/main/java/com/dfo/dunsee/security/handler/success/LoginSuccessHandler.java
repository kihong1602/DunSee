package com.dfo.dunsee.security.handler.success;

import static com.dfo.dunsee.common.ResultType.SUCCESS;
import static com.dfo.dunsee.common.RoleType.ADMIN;
import static com.dfo.dunsee.common.RoleType.MANAGER;
import static com.dfo.dunsee.common.RoleType.USER;
import static com.dfo.dunsee.common.ServiceCode.MBR201;
import static com.dfo.dunsee.common.ServiceCode.MBR202;
import static com.dfo.dunsee.common.ServiceCode.setServiceMsg;

import com.dfo.dunsee.common.JsonUtils;
import com.dfo.dunsee.common.ServiceCode;
import com.dfo.dunsee.common.response.ResponseJson;
import com.dfo.dunsee.security.auth.oauth.PrincipalDetails;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

  private final JsonUtils jsonUtils;
  private final ServiceCode NORMAL_SERVICE_CODE = MBR201;
  private final ServiceCode OAUTH_SERVICE_CODE = MBR202;
  private final RequestCache requestCache = new HttpSessionRequestCache();
  private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {

    String provider = getProviderName(authentication);

    switch (provider) {
      case "dunsee" -> jsonLoginRedirectStrategy(request, response, authentication);
      case "kakao", "google" -> oAuth2LoginRedirectStrategy(request, response, authentication);
    }

  }

  protected void jsonLoginRedirectStrategy(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    log.info(setServiceMsg(NORMAL_SERVICE_CODE) + "로그인 성공");

    setSecurityContextToSession(authentication, request);

    SavedRequest savedRequest = requestCache.getRequest(request, response);
    ResponseJson responseJson;

    if (savedRequest != null) {
      String previousUrl = savedRequest.getRedirectUrl();
      responseJson = ResponseJson.setResponseJson(NORMAL_SERVICE_CODE, SUCCESS, previousUrl);
    } else {
      String determineUrl = determineTargetUrl(authentication);
      responseJson = ResponseJson.setResponseJson(NORMAL_SERVICE_CODE, SUCCESS, determineUrl);
    }

    jsonUtils.sendResponseJson(NORMAL_SERVICE_CODE, response, responseJson);
  }

  protected void oAuth2LoginRedirectStrategy(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    log.info(setServiceMsg(OAUTH_SERVICE_CODE) + "로그인 성공");

    SavedRequest savedRequest = requestCache.getRequest(request, response);

    if (savedRequest != null) {
      String previousUrl = savedRequest.getRedirectUrl();
      redirectStrategy.sendRedirect(request, response, previousUrl);
    } else {
      String determineUrl = determineTargetUrl(authentication);
      redirectStrategy.sendRedirect(request, response, determineUrl);
    }
  }

  protected String determineTargetUrl(final Authentication authentication) {
    Map<String, String> roleTargetUrlMap = new HashMap<>();
    roleTargetUrlMap.put(USER.getValue(), "/");
    roleTargetUrlMap.put(MANAGER.getValue(), "/manager");
    roleTargetUrlMap.put(ADMIN.getValue(), "/admin");

    final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    return authorities.stream()
                      .map(GrantedAuthority::getAuthority)
                      .filter(roleTargetUrlMap::containsKey)
                      .findFirst()
                      .map(roleTargetUrlMap::get)
                      .orElseThrow(IllegalStateException::new);
  }

  protected void setSecurityContextToSession(Authentication authentication, HttpServletRequest request) {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    HttpSession session = request.getSession();
    securityContext.setAuthentication(authentication);
    session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);
  }

  private String getProviderName(Authentication authentication) {
    return ((PrincipalDetails) authentication.getPrincipal()).getMember()
                                                             .getProvider();
  }
}
