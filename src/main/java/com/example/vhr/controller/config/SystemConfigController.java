package com.example.vhr.controller.config;

import com.example.vhr.bean.Menu;
import com.example.vhr.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system/config")
public class SystemConfigController {
    @Autowired
    MenuService menuService;
    @RequestMapping("/menu")
    public List<Menu> getMenuByHrId(){
         return menuService.getMenuByHrId();
    }
}
