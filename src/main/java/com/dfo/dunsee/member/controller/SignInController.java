package com.dfo.dunsee.member.controller;

import static com.dfo.dunsee.common.ServiceCode.MBR101;
import static com.dfo.dunsee.common.ServiceCode.MBR201;
import static com.dfo.dunsee.common.ServiceCode.MBR202;

import com.dfo.dunsee.common.ResultType;
import com.dfo.dunsee.common.ServiceCode;
import com.dfo.dunsee.common.response.ResponseJson;
import com.dfo.dunsee.member.dto.JoinMemberInfo;
import com.dfo.dunsee.member.service.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SignInController {

  private final RegisterService memberService;

  @GetMapping("/register")
  public ModelAndView register(ModelAndView modelAndView) {
    log.info(ServiceCode.setServiceMsg(MBR201) + ServiceCode.setServiceMsg(MBR202) + "회원가입 페이지 이동");

    modelAndView.setViewName("member/register");
    return modelAndView;
  }


  @PostMapping("/register-process")
  public ResponseEntity<ResponseJson> registerId(@RequestBody JoinMemberInfo joinMemberInfo) {

    log.info(ServiceCode.setServiceMsg(MBR101) + "일반 회원가입 진행");

    ResultType result = memberService.memberRegister(MBR101, joinMemberInfo);

    ResponseJson responseJson = ResponseJson.setResponseJson(MBR101, result, "회원가입이 완료되었습니다. 로그인페이지로 이동합니다.");

    return switch (result) {
      case SUCCESS -> ResponseEntity.ok(responseJson);

      case FAILURE -> ResponseEntity.notFound().build();
      default -> ResponseEntity.badRequest().build();
    };

  }

  @GetMapping("/user")
  public String user() {
    return "user";
  }

  @GetMapping("/favorite")
  public String favorite() {
    return "즐겨찾기 페이지";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public String admin() {
    return "관리자페이지";
  }
}
