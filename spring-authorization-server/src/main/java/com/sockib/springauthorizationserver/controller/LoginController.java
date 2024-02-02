package com.sockib.springauthorizationserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping(path = "/signin")
    String getLoginPage() {
        return "signin";
    }

}
