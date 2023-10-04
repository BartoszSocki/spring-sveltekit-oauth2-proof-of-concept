package com.sockib.springresourceserver.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(path = "/api")
public class BasicController {

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('test.read')")
    String test() {
        return "success";
    }

    @GetMapping("/principal")
    @PreAuthorize("hasAuthority('test.read')")
    String principal(Authentication authentication) {
        return authentication.getName();
    }

}
