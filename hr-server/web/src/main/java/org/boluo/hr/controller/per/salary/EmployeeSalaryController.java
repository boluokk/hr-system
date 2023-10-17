package org.boluo.hr.controller.per.salary;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.boluo.hr.pojo.AdjustSalary;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.service.AdjustSalaryService;
import org.boluo.hr.service.EmployeeRewardPunishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 员工工资信息
 *
 * @author 🍍
 * @date 2023/10/09
 */

@RestController
@RequestMapping("/per/salary")
public class EmployeeSalaryController {

    private final AdjustSalaryService adjustSalaryService;
    private final EmployeeRewardPunishmentService employeeRewardPunishmentService;

    @Autowired
    public EmployeeSalaryController(AdjustSalaryService adjustSalaryService,
                                    EmployeeRewardPunishmentService employeeRewardPunishmentService) {
        this.adjustSalaryService = adjustSalaryService;
        this.employeeRewardPunishmentService = employeeRewardPunishmentService;
    }

    /**
     * 员工工资分页
     */
    @GetMapping("/{pageNum}/{pageSize}")
    public RespBean findPage(@PathVariable("pageNum") Integer pageNum,
                             @PathVariable("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return RespBean.ok(new PageInfo<>(adjustSalaryService.selectAll()));
    }

    /**
     * 员工工资修改
     */
    @PutMapping("/modify")
    public RespBean modify(AdjustSalary adjustSalary) {
        if (adjustSalaryService.update(adjustSalary)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    /**
     * 员工工资删除
     */
    @DeleteMapping("/delete/{id}")
    public RespBean remove(@PathVariable("id") Integer id) {
        if (adjustSalaryService.delete(id)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    /**
     * 员工工资新增
     */
    @PutMapping("/add/{workId}")
    public RespBean add(AdjustSalary adjustSalary,
                        @PathVariable("workId") String workId) {
        Integer employeeId = employeeRewardPunishmentService.selectByWorkId(workId);
        if (employeeId == null) {
            return RespBean.error("未找到当前员工");
        }
        adjustSalary.setEmployeeId(employeeId);
        if (adjustSalaryService.insert(adjustSalary)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }
}
