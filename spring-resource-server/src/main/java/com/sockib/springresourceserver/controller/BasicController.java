package com.sockib.springresourceserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api")
public class BasicController {

    private final ObjectMapper objectMapper;

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('test.read')")
    String test() {
        return "success";
    }

    @GetMapping(value = "/principal", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('test.read')")
    String principal(Authentication authentication) throws JsonProcessingException {
        return objectMapper.writeValueAsString(new success("success"));
    }

    private record success(String success) {

    }

}
