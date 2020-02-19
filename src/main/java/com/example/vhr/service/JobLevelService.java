package com.example.vhr.service;

import com.example.vhr.bean.JobLevel;
import com.example.vhr.mapper.JobLevelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JobLevelService {
    @Autowired
    JobLevelMapper jobLevelMapper;

    public List<JobLevel> getAllJobLevel(){
        return jobLevelMapper.getAllJobLevel();
    }

    public Integer addJobLevel(JobLevel jobLevel){
        jobLevel.setCreateDate(new Date());
        jobLevel.setEnabled(true);
        return jobLevelMapper.insertSelective(jobLevel);
    }

    public Integer updateJobLevel(JobLevel JobLevel){
        return jobLevelMapper.updateByPrimaryKeySelective(JobLevel);
    }

    public Integer deleteJobLevel(Integer id){
        return jobLevelMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteJobLevelsByIds(Integer[] ids){
        return jobLevelMapper.deleteJobLevelsByIds(ids);
    }
}
