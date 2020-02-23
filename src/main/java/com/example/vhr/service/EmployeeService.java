package com.example.vhr.service;

import com.example.vhr.bean.Employee;
import com.example.vhr.bean.RespPageBean;
import com.example.vhr.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    public RespPageBean getEmployeeByPage(Integer page, Integer size){
        if (page != null && size != null){
            page = (page - 1) * size;
        }
        List<Employee> data = employeeMapper.getEmployeeByPage(page,size);
        Long total = employeeMapper.getTotal();
        RespPageBean respPageBean = new RespPageBean();
        respPageBean.setTotal(total);
        respPageBean.setData(data);
        return respPageBean;
    }
}
