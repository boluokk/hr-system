package org.boluo.hr.controller.per.salary;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.boluo.hr.pojo.BaseAdjustSalary;
import org.boluo.hr.pojo.Employee;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.service.AdjustSalaryService;
import org.boluo.hr.service.EmployeeService;
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
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeSalaryController(AdjustSalaryService adjustSalaryService,
                                    EmployeeService employeeService) {
        this.adjustSalaryService = adjustSalaryService;
        this.employeeService = employeeService;
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
    public RespBean modify(BaseAdjustSalary baseAdjustSalary) {
        if (adjustSalaryService.update(baseAdjustSalary)) {
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
    public RespBean add(BaseAdjustSalary baseAdjustSalary,
                        @PathVariable("workId") String workId) {
        Employee employee = employeeService.selectEmployeeByWorkId(workId);
        if (employee == null) {
            return RespBean.error("未找到当前员工");
        }
        baseAdjustSalary.setEmployeeId(employee.getId());
        if (adjustSalaryService.insert(baseAdjustSalary)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }
}
