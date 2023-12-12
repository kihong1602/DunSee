package com.dfo.dunsee.member.controller;

import com.dfo.dunsee.common.ServiceCode;
import com.dfo.dunsee.search.dto.CharacterSearchKeyword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
  public String indexPage(Model model) {
    log.info(ServiceCode.setServiceMsg(ServiceCode.COM100).replace("::", "").trim());
    model.addAttribute("searchKeyword", CharacterSearchKeyword.builder().build());
    return "index";
  }
}
