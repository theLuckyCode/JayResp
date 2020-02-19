package com.example.vhr.mapper;

import com.example.vhr.bean.Position;
import com.example.vhr.bean.RespBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PositionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Position record);

    int insertSelective(Position record);

    Position selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Position record);

    int updateByPrimaryKey(Position record);

    List<Position> getAllPosition();

//@param注解是一个参数注解
//在mybaitis的dao层使用。在mybatis中我们常常要使用到多个对象参数，但是在xml中的parameterType往往只能指定一个参数类型。
//为了解决这个问题，我们可以在dao的接口中使用@param注解，多个参数可以使用多个@param注解。
//这样就可以将参数传入mybatis的sql语句中了。使用的时候不用在xml中继续使用parameterType=“XXXXXX”  了。
    Integer deletePositionsByIds(@Param("ids") Integer[] ids);
}