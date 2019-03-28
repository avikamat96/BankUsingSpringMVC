package com.epam.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {

    @GetMapping("/") 
    public String indexController() { 
      return "login"; 
      }
    
    @GetMapping("/dashboard") 
    public String showDashboard() { 
      return "dashboard"; 
      }
   
}
