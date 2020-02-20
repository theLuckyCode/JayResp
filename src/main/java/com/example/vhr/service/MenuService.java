package com.example.vhr.service;

import com.example.vhr.bean.Hr;
import com.example.vhr.bean.Menu;
import com.example.vhr.mapper.MenuMapper;
import com.example.vhr.mapper.MenuRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuService {
    @Autowired
    MenuMapper menuMapper;

    @Autowired
    MenuRoleMapper menuRoleMapper;

    public List<Menu> getMenuByHrId() {
        //在SpringSecurity中拿到用户的信息
        return menuMapper.getMenuByHrId(((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }

    //  @Cacheable可以标记在一个方法上，也可以标记在一个类上。
//  当标记在一个方法上时表示该方法是支持缓存的，当标记在一个类上时则表示该类所有的方法都是支持缓存的
//  @Cacheable
    public List<Menu> getAllMenuWithRole() {
        return menuMapper.getAllMenuWithRole();
    }

    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }

    public List<Integer> getMidsByRid(Integer rid) {
        return menuMapper.getMidsByRid(rid);
    }

    @Transactional  //开启事务
    public boolean updateMenuRole(Integer rid, Integer[] mids) {
        menuRoleMapper.deleteByRid(rid);  //先删除旧的
        Integer result = menuRoleMapper.insertRecord(rid,mids);  //再增加新的
        return result==mids.length;
    }
}
