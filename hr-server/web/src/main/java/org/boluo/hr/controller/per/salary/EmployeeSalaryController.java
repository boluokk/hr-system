package org.boluo.hr.controller.per.salary;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.boluo.hr.annotation.Log;
import org.boluo.hr.pojo.InsertAdjustSalary;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.pojo.UploadAdjustSalary;
import org.boluo.hr.pojo.UploadEmployee;
import org.boluo.hr.service.AdjustSalaryService;
import org.boluo.hr.service.EmployeeService;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * 员工工资信息
 *
 * @author 🍍
 * @date 2023/10/09
 */

@RestController
@RequestMapping("/per/salary")
@Validated
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
    @Log("查询员工工资分页")
    public RespBean findPage(@Min(value = 1, message = "页码不能小于1")
                             @PathVariable("pageNum") Integer pageNum,
                             @Min(value = 1, message = "页大小不能小于1")
                             @Max(value = 10, message = "页大小不能大于10")
                             @PathVariable("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return RespBean.ok(new PageInfo<>(adjustSalaryService.selectAll()));
    }

    /**
     * 员工工资修改
     */
    @PutMapping("/modify")
    @Log("员工工资修改")
    public RespBean modify(@Valid @RequestBody UploadAdjustSalary uploadAdjustSalary) {
        if (adjustSalaryService.update(uploadAdjustSalary)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    /**
     * 员工工资删除
     */
    @DeleteMapping("/delete/{id}")
    @Log("员工工资删除")
    public RespBean remove(@Min(value = 1, message = "id不能小于1")
                           @PathVariable("id") Integer id) {
        if (adjustSalaryService.delete(id)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    /**
     * 新增员工工资
     */
    @PutMapping("/add/{workId}")
    @Log("新增员工工资")
    public RespBean add(@Valid @RequestBody InsertAdjustSalary insertAdjustSalary,
                        @Length(min = 8, max = 8, message = "工号长度必须为8位")
                        @PathVariable("workId") String workId) {
        UploadEmployee employee = employeeService.selectEmployeeByWorkId(workId);
        if (employee == null) {
            return RespBean.error("未找到当前员工");
        }
        insertAdjustSalary.setEmployeeId(employee.getId());
        if (adjustSalaryService.insert(insertAdjustSalary)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }
}
