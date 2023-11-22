package com.dfo.dunsee.member.Controller;

import static com.dfo.dunsee.common.ServiceCode.MBR101;

import com.dfo.dunsee.common.ResultType;
import com.dfo.dunsee.common.response.ResponseJson;
import com.dfo.dunsee.member.dto.JoinMemberInfo;
import com.dfo.dunsee.member.service.MemberRegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SignInController {

  private final MemberRegisterService memberService;

  @GetMapping("/register")
  public String register() {
    return "member/register";
  }


  @PostMapping("/register-process")
  @ResponseBody
  public ResponseEntity<ResponseJson> registerId(@RequestBody JoinMemberInfo joinMemberInfo) {
    String stateLog = MBR101.name() + " | " + MBR101.getDescription();
    log.info(stateLog);

    ResultType result = memberService.memberRegister(MBR101, joinMemberInfo);

    ResponseJson responseJson = ResponseJson.setResponseJson(MBR101, result);

    return switch (result) {
      case SUCCESS -> ResponseEntity.ok(responseJson);

      case FAILURE -> ResponseEntity.notFound()
                                    .build();
    };

  }

  @GetMapping("/user")
  @ResponseBody
  public String user() {
    return "user";
  }

  @GetMapping("/favorite")
  @ResponseBody
  public String favorite() {
    return "즐겨찾기 페이지";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ResponseBody
  public String admin() {
    return "관리자페이지";
  }
}
