package com.dfo.dunsee.member.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SignInController {

  @GetMapping("/register")
  public String signIn() {
    return "register";
  }

  @GetMapping("/user")
  @ResponseBody
  public String user() {
    return "user";
  }
}
