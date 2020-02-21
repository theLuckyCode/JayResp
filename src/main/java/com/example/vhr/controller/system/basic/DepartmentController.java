package com.example.vhr.controller.system.basic;

import com.example.vhr.bean.Department;
import com.example.vhr.bean.RespBean;
import com.example.vhr.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @GetMapping("/")
    public List<Department> getAllDepartment(){
        return departmentService.getAllDepartment();
    }

    @PostMapping("/")
    public RespBean addDep(@RequestBody Department department){
        departmentService.addDep(department);
        if (department.getResult()>0){
            return RespBean.success("添加成功！",department);
        }
        return RespBean.error("添加失败！",department);
    }

    @DeleteMapping("/{id}")
    public RespBean deleteDep(@PathVariable Integer id){
        System.out.println(id);
        Department department = new Department();
        department.setId(id);
        departmentService.deleteDep(department);
        if (department.getResult()==-2){
            return RespBean.error("该部门还有子部门，删除失败");
        }else if(department.getResult()==-1){
            return RespBean.error("该部门还有员工，删除失败");
        }else if (department.getResult()>0){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
