package com.boluo.hr.controller.system.basic;

import org.boluo.hr.pojo.Joblevel;
import org.boluo.hr.pojo.RespBean;
import com.boluo.hr.service.JobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author @1352955539(boluo)
 * @date 2021/2/6 - 18:01
 */
@RestController
@RequestMapping("/system/basic/jobname")
public class JobLevelController {

    @Autowired
    private JobLevelService jobLevelService;

    @GetMapping("/")
    public RespBean getAlldata() {
        return  RespBean.ok(jobLevelService.getALLJobLevel());
    }

    @DeleteMapping("/{id}")
    public RespBean deleteByOne(@PathVariable Integer id) {
        int i = jobLevelService.deleteJobById(id);
        if(i==1) {
            return RespBean.ok("删除成功！");
        } else {
            return RespBean.error("删除失败！");
        }
    }

    @PutMapping("/up")
    public RespBean updateByOne(Joblevel job) {
        int i = jobLevelService.updateJobByJobPojo(job);
        if(i==1) {
            return RespBean.ok("修改成功！");
        } else {
            return RespBean.error("修改失败！");
        }
    }

    @PutMapping("/")
    public RespBean addJob(Joblevel job) {
        int i = jobLevelService.insertJobByJobLevelPojo(job);
        if(i==1) {
            return RespBean.ok("添加成功！");
        } else {
            return RespBean.error("添加失败！");
        }
    }
}
