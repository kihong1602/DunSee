package com.dfo.dunsee.domain.member.controller;

import com.dfo.dunsee.common.Server;
import com.dfo.dunsee.common.ServiceCode;
import com.dfo.dunsee.domain.search.dto.CharacterSearchKeyword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class LoginController {

  private static final Server[] SERVERS = Server.values();

  @GetMapping("/login")
  public String loginPage() {
    log.info(ServiceCode.setServiceMsg(ServiceCode.MBR201) + "로그인페이지 이동");
    return "member/login";
  }

  @GetMapping("/")
  public String indexPage(Model model) {
    log.info(ServiceCode.setServiceMsg(ServiceCode.COM100).replace("::", "").trim());
    model.addAttribute("searchKeyword", CharacterSearchKeyword.builder().build());
    model.addAttribute("servers", SERVERS);
    return "index";
  }
}
