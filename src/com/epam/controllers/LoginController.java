package com.epam.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
  
  @PostMapping("/auth")
  public String doPost(@RequestParam("employeeid") String employeeId,
      @RequestParam("accountpassword") String password) {
    if (employeeId.equals("1111") && password.equals("1111")) {
      return "dashboard";
    }
    return "login";
  }
}
