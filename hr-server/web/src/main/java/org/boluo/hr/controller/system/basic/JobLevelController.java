package org.boluo.hr.controller.system.basic;

import org.boluo.hr.annotation.Log;
import org.boluo.hr.pojo.InsertJobLevel;
import org.bluo.global.pojo.RespBean;
import org.boluo.hr.pojo.UploadJobLevel;
import org.boluo.hr.service.JobLevelService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Date;

/**
 * 职称等级
 *
 * @author 🍍
 * @date 2023/10/1
 */
@RestController
@RequestMapping("/system/basic/jobName")
@Validated
public class JobLevelController {

    @Resource
    private JobLevelService jobLevelService;

    /**
     * 获取所有职称等级信息
     */
    @GetMapping("/")
    @Log("获取所有职称等级信息")
    public RespBean findAll() {
        return RespBean.ok(jobLevelService.selectAll());
    }

    /**
     * 删除职称等级
     */
    @DeleteMapping("/delete/{id}")
    @Log("删除职称等级")
    public RespBean remove(@Min(value = 1, message = "id不合法")
                           @PathVariable("id") Integer id) {
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
    @Log("修改职称等级")
    public RespBean modify(@Valid @RequestBody UploadJobLevel uploadJobLevel) {
        if (jobLevelService.update(uploadJobLevel)) {
            return RespBean.ok();
        } else {
            return RespBean.error();
        }
    }

    /**
     * 新增职称等级
     */
    @PutMapping("/add")
    @Log("新增职称等级")
    public RespBean add(@Valid @RequestBody InsertJobLevel insertJobLevel) {
        insertJobLevel.setCreateDate(new Date());
        if (jobLevelService.insert(insertJobLevel)) {
            return RespBean.ok();
        } else {
            return RespBean.error();
        }
    }
}
