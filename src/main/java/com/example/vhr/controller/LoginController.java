package com.example.vhr.controller;

import com.example.vhr.bean.RespBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @RequestMapping("/login")
    public RespBean login(){
        return RespBean.error("您尚未登录，请先登录！");
    }
}
