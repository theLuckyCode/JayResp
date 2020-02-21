package com.example.vhr.service;

import com.example.vhr.bean.Department;
import com.example.vhr.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;

    public List<Department> getAllDepartment(){
        //这次使用的是递归的方式，因为子部门比较多，不想menu那样
        return departmentMapper.getAllDepartment(-1);
    }

    public void addDep(Department department){
        department.setEnabled(true);
        departmentMapper.addDep(department);
    }

    public void deleteDep(Department department){
        departmentMapper.deleteDep(department);
    }
}
