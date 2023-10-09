package org.boluo.hr.controller.system.basic;

import org.boluo.hr.pojo.Joblevel;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.service.JobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 🍍
 * @date 2023/10/1
 */
@RestController
@RequestMapping("/system/basic/jobName")
public class JobLevelController {

    private final JobLevelService jobLevelService;

    @Autowired
    public JobLevelController(JobLevelService jobLevelService) {
        this.jobLevelService = jobLevelService;
    }

    @GetMapping("/")
    public RespBean findAll() {
        return RespBean.ok(jobLevelService.selectAll());
    }

    @DeleteMapping("/delete/{id}")
    public RespBean remove(@PathVariable Integer id) {
        if (jobLevelService.delete(id)) {
            return RespBean.ok();
        } else {
            return RespBean.error();
        }
    }

    @PutMapping("/modify")
    public RespBean modify(Joblevel job) {
        if (jobLevelService.update(job)) {
            return RespBean.ok();
        } else {
            return RespBean.error();
        }
    }

    @PutMapping("/add")
    public RespBean add(Joblevel job) {
        if (jobLevelService.insert(job)) {
            return RespBean.ok();
        } else {
            return RespBean.error();
        }
    }
}
