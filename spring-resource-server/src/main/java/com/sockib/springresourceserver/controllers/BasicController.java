package com.sockib.springresourceserver.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class BasicController {

    @GetMapping("/hello")
    @PreAuthorize("hasRole('USER')")
    String hello() {
        return "hello";
    }

}
