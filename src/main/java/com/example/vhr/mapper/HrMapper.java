package com.example.vhr.mapper;

import com.example.vhr.bean.Hr;
import com.example.vhr.bean.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HrMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Hr record);

    int insertSelective(Hr record);

    Hr selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Hr record);

    int updateByPrimaryKey(Hr record);

    Hr loadUserByUsername(String username);

    List<Role> getHrRoleById(Integer id);

    List<Hr> getAllHrs(@Param("hrId") Integer hrId, @Param("keywords") String keywords);
}