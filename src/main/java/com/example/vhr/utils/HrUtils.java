package com.example.vhr.utils;

import com.example.vhr.bean.Hr;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


public class HrUtils {
//  获取当前登录的对象
    public static Hr getCurrentHr(){
        return ((Hr)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
