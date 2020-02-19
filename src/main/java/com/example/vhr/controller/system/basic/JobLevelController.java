package com.example.vhr.controller.system.basic;

import com.example.vhr.bean.JobLevel;
import com.example.vhr.bean.RespBean;
import com.example.vhr.service.JobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic/joblevel")
public class JobLevelController {
    @Autowired
    JobLevelService jobLevelService;

    @GetMapping("/")
    public List<JobLevel> getAllJobLevel(){
        return jobLevelService.getAllJobLevel();
    }

    @PostMapping("/")
    public RespBean addJobLevel(@RequestBody JobLevel jobLevel){
        if (jobLevelService.addJobLevel(jobLevel)>0){
            return RespBean.success("添加成功!");
        }
        return RespBean.error("添加失败！");
    }

    @PutMapping("/")
    public RespBean updateJobLevel(@RequestBody JobLevel jobLevel){
        if (jobLevelService.updateJobLevel(jobLevel)>0){
            return RespBean.success("修改成功！");
        }
        return RespBean.error("修改失败！");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteJobLevel(@PathVariable Integer id){
         if (jobLevelService.deleteJobLevel(id)>0){
             return RespBean.success("删除成功");
         }
        return RespBean.error("删除失败");
    }

    @DeleteMapping("/")
    public RespBean deleteJobLevelsByIds(Integer[] ids){
        if (jobLevelService.deleteJobLevelsByIds(ids)==ids.length){
            return RespBean.success("批量操作成功！");
        }
        return RespBean.error("批量操作失败！");
    }
}
