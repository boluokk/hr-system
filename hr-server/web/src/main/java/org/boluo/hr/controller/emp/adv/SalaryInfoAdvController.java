package org.boluo.hr.controller.emp.adv;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.bluo.hr.service.api.pojo.SalaryTableSearch;
import org.boluo.hr.annotation.Log;
import org.bluo.global.pojo.RespBean;
import org.boluo.hr.service.SalaryTableService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

/**
 * 工资信息
 *
 * @author 🍍
 * @date 2023/10/1
 */
@RestController
@RequestMapping("/emp/adv/salaryInfo")
@Validated
public class SalaryInfoAdvController {

    @Resource
    private SalaryTableService salaryTableService;


    /**
     * 工资信息分页
     */
    @GetMapping("/{pageNum}/{pageSize}")
    @Log("查询工资信息分页")
    public RespBean findByPage(@Min(value = 1, message = "页码不能小于1")
                               @PathVariable("pageNum") Integer pageNum,
                               @Min(value = 1, message = "页大小不能小于1")
                               @Max(value = 10, message = "页大小不能大于10")
                               @PathVariable("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return RespBean.ok(new PageInfo<>(
                salaryTableService.selectAll(new SalaryTableSearch().setDate(new Date()))));
    }
}
