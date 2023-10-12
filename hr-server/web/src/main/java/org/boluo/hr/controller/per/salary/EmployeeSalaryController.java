package org.boluo.hr.controller.per.salary;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.boluo.hr.pojo.Adjustsalary;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.service.AdjustSalaryService;
import org.boluo.hr.service.EmployeecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 🍍
 * @date 2023/10/09
 */

@RestController
@RequestMapping("/per/salary")
public class EmployeeSalaryController {

    private final AdjustSalaryService adjustsalaryService;
    private final EmployeecService employeecService;

    @Autowired
    public EmployeeSalaryController(AdjustSalaryService adjustsalaryService, EmployeecService employeecService) {
        this.employeecService = employeecService;
        this.adjustsalaryService = adjustsalaryService;
    }

    @GetMapping("/{pageNum}/{pageSize}")
    public RespBean findPage(@PathVariable("pageNum") Integer pageNum,
                             @PathVariable("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return RespBean.ok(new PageInfo<>(adjustsalaryService.selectAll()));
    }


    @PutMapping("/modify")
    public RespBean modify(Adjustsalary adjustsalary) {
        if (adjustsalaryService.update(adjustsalary)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    @DeleteMapping("/delete/{id}")
    public RespBean remove(@PathVariable("id") Integer id) {
        if (adjustsalaryService.delete(id)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    @PutMapping("/add/{workId}")
    public RespBean add(Adjustsalary adjustsalary,
                        @PathVariable("workId") String workId) {
        Integer employeeId = employeecService.selectByWorkId(workId);
        if (employeeId == null) {
            return RespBean.error("未找到当前员工");
        }
        adjustsalary.setEid(employeeId);
        if (adjustsalaryService.insert(adjustsalary)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }
}
