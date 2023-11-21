package com.dfo.dunsee.member.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SignInController {

  @GetMapping("/register")
  public String register() {
    return "member/register";
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
