package com.example.vhr.service;

import com.example.vhr.bean.Position;
import com.example.vhr.bean.RespBean;
import com.example.vhr.mapper.PositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PositionService {
    @Autowired
    PositionMapper positionMapper;

    public List<Position> getAllPosition(){
        return positionMapper.getAllPosition();
    }

    public Integer addPosition(Position position){
        position.setEnabled(true);
        position.setCreateDate(new Date());
        return positionMapper.insertSelective(position);
    }

    public Integer updatePosition(Position position){
       return positionMapper.updateByPrimaryKeySelective(position);
    }

    public Integer deletePosition(Integer id){
        return positionMapper.deleteByPrimaryKey(id);
    }

    public Integer deletePositionsByIds(Integer[] ids){
        return positionMapper.deletePositionsByIds(ids);
    }
}
