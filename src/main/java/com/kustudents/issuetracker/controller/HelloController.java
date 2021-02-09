package com.kustudents.issuetracker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Hello World!";
    }

}
