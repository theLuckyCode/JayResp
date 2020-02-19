package com.example.vhr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/hello")
    public String hello() {
        return "hello!";
    }

    @GetMapping("/employee/advanced/test2")
    public String test2() {
        return "/employee/advanced/test2";
    }

    @GetMapping("/employee/basic/test3")
    public String test3() {
        return "/employee/basic/test3";
    }
}
