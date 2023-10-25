package org.boluo.hr.controller.emp.adv;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.boluo.hr.annotation.Log;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.pojo.SalaryTableSearch;
import org.boluo.hr.service.SalaryTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 工资信息
 *
 * @author 🍍
 * @date 2023/10/1
 */
@RestController
@RequestMapping("/emp/adv/salaryInfo")
public class SalaryInfoAdvController {

    private final SalaryTableService salaryTableService;

    @Autowired
    public SalaryInfoAdvController(SalaryTableService salaryTableService) {
        this.salaryTableService = salaryTableService;
    }

    /**
     * 工资信息分页
     */
    @GetMapping("/{pageNum}/{pageSize}")
    @Log("查询工资信息分页")
    public RespBean findByPage(@PathVariable("pageNum") Integer pageNum,
                               @PathVariable("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return RespBean.ok(new PageInfo<>(
                salaryTableService.selectAll(new SalaryTableSearch().setDate(new Date()))));
    }
}
