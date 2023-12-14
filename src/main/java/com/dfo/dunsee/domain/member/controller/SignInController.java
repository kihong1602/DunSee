package com.dfo.dunsee.domain.member.controller;

import static com.dfo.dunsee.common.ServiceCode.MBR101;
import static com.dfo.dunsee.common.ServiceCode.MBR201;
import static com.dfo.dunsee.common.ServiceCode.MBR202;
import static com.dfo.dunsee.common.ServiceCode.setServiceMsg;

import com.dfo.dunsee.common.ResultType;
import com.dfo.dunsee.common.response.ResponseJson;
import com.dfo.dunsee.domain.member.dto.JoinMemberInfo;
import com.dfo.dunsee.domain.member.service.RegisterService;
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
    log.info(setServiceMsg(MBR201) + setServiceMsg(MBR202) + "회원가입 페이지 이동");

    modelAndView.setViewName("member/register");
    return modelAndView;
  }


  @PostMapping("/register-process")
  public ResponseEntity<ResponseJson> registerId(@RequestBody JoinMemberInfo joinMemberInfo) {

    log.info(setServiceMsg(MBR101) + "일반 회원가입 진행");

    ResultType result = memberService.memberRegister(MBR101, joinMemberInfo);

    ResponseJson responseJson = setResponseJson(result);

    return switch (result) {
      case SUCCESS -> ResponseEntity.ok().body(responseJson);
      case FAILURE -> ResponseEntity.badRequest().body(responseJson);
      case EXIST -> ResponseEntity.unprocessableEntity().body(responseJson);
    };

  }

  private ResponseJson setResponseJson(ResultType resultType) {
    String msg =
        switch (resultType) {
          case SUCCESS -> "회원가입이 완료되었습니다. 로그인페이지로 이동합니다.";
          case FAILURE -> "회원가입에 실패하였습니다. 다시 시도해주세요.";
          case EXIST -> "이미 등록된 아이디 또는 이메일입니다.";
        };

    return ResponseJson.setResponseJson(MBR101, resultType, msg);
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
