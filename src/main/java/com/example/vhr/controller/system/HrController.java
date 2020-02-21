package com.example.vhr.controller.system;

import com.example.vhr.bean.Hr;
import com.example.vhr.bean.RespBean;
import com.example.vhr.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/hr")
public class HrController {
    @Autowired
    HrService hrService;

    @GetMapping("/")
    public List<Hr> getAllHrs(){
        return hrService.getAllHrs();
    }

    @PutMapping("/")
    public RespBean updateHr(@RequestBody Hr hr){
        if (hrService.updateHr(hr)>0){
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }
}
