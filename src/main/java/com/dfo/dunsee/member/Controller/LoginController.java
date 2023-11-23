package com.dfo.dunsee.member.Controller;

import com.dfo.dunsee.common.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class LoginController {

  @GetMapping("/login")
  public String loginPage() {
    log.info(ServiceCode.setServiceMsg(ServiceCode.MBR201) + "로그인페이지 이동");
    return "member/login";
  }

  @GetMapping("/")
  public String indexPage() {
    log.info(ServiceCode.setServiceMsg(ServiceCode.COM100)
                        .replace("::", "")
                        .trim());
    return "/index";
  }
}
