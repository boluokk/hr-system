package org.boluo.hr.controller.system.basic;

import org.boluo.hr.pojo.JobLevel;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.service.JobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 职称等级
 *
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

    /**
     * 获取所有职称等级信息
     */
    @GetMapping("/")
    public RespBean findAll() {
        return RespBean.ok(jobLevelService.selectAll());
    }

    /**
     * 删除职称等级
     *
     * @param id 职称等级id
     */
    @DeleteMapping("/delete/{id}")
    public RespBean remove(@PathVariable Integer id) {
        if (jobLevelService.delete(id)) {
            return RespBean.ok();
        } else {
            return RespBean.error();
        }
    }

    /**
     * 修改职称等级
     */
    @PutMapping("/modify")
    public RespBean modify(@RequestBody JobLevel jobLevel) {
        if (jobLevelService.update(jobLevel)) {
            return RespBean.ok();
        } else {
            return RespBean.error();
        }
    }

    /**
     * 新增职称等级
     */
    @PutMapping("/add")
    public RespBean add(@RequestBody JobLevel jobLevel) {
        if (jobLevelService.insert(jobLevel)) {
            return RespBean.ok();
        } else {
            return RespBean.error();
        }
    }
}
