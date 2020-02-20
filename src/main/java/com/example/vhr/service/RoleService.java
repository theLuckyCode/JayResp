package com.example.vhr.service;

import com.example.vhr.bean.Role;
import com.example.vhr.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleMapper roleMapper;

    public List<Role> getAllRoles(){
        return roleMapper.getAllRoles();
    }

    public Integer addRole(Role role){
        if (!role.getName().startsWith("ROLE_")){
            role.setName("ROLE_"+role.getName());
        }
        return roleMapper.insert(role);
    }

    public Integer deleteRole(Integer id){
        return roleMapper.deleteByPrimaryKey(id);
    }
}
