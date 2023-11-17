package com.dfo.dunsee.viewPage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class viewPageController {

  @GetMapping("/")
  public String index() {
    return "index";
  }
}
