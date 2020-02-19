package com.example.vhr.controller.system.basic;

import com.example.vhr.bean.Position;
import com.example.vhr.bean.RespBean;
import com.example.vhr.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {
    @Autowired
    PositionService positionService;

    @GetMapping("/")
    public List<Position> getAllPosition() {
        return positionService.getAllPosition();
    }

    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position){
    //@requestBody注解常用来处理content-type不是默认的application/x-www-form-urlcoded编码的内容，
    // 比如说：application/json或者是application/xml等。一般情况下来说常用其来处理application/json类型。
        if (positionService.addPosition(position)>0){
            return RespBean.success("添加成功!");
        }
        return RespBean.error("添加失败！");
    }

    @PutMapping("/")
    public RespBean updatePosition(@RequestBody Position position){
        if (positionService.updatePosition(position)>0){
           return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @DeleteMapping("/{id}")
    public RespBean deletePosition(@PathVariable Integer id){
        //通过 @PathVariable 可以将 URL 中占位符参数绑定到控制器处理方法的入参中:URL 中的 {xxx} 占位符可以通过
        if (positionService.deletePosition(id)>0){
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    //批量删除，传过来的参数既可以用数组形式，也可以用字符串形式
    @DeleteMapping("/")
    public RespBean deletePositionsByIds(Integer[] ids){
       if (positionService.deletePositionsByIds(ids)==ids.length){
           return RespBean.success("批量操作成功！");
       }
       return RespBean.error("批量操作失败！");
    }
}
