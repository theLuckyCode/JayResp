package com.example.vhr.controller.system.basic;

import com.example.vhr.bean.Menu;
import com.example.vhr.bean.RespBean;
import com.example.vhr.bean.Role;
import com.example.vhr.service.MenuService;
import com.example.vhr.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic/permiss")
public class PermissController {

    @Autowired
    RoleService roleService;

    @Autowired
    MenuService menuService;

    @GetMapping("/")
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }

    @GetMapping("/menus")
    public List<Menu> getAllMenus(){
        return menuService.getAllMenus();
    }

    @GetMapping("/mids/{rid}")
    public List<Integer> getMidsByRid(@PathVariable Integer rid){
        return menuService.getMidsByRid(rid);
    }

    @PutMapping("/")
    public RespBean updateMenuRole(Integer rid,Integer[] mids){
        if (menuService.updateMenuRole(rid,mids)){
            return RespBean.success("修改成功！");
        }
        return RespBean.error("修改失败！");
    }

    @PostMapping("/role")
    public  RespBean addRole(@RequestBody Role role){
        if (roleService.addRole(role)>0){
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @DeleteMapping("/")
    public RespBean deleteRole(Integer id){
        if (roleService.deleteRole(id)>0){
               return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }
}
