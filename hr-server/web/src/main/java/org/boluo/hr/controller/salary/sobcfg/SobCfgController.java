package org.boluo.hr.controller.salary.sobcfg;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.boluo.hr.annotation.Log;
import org.boluo.hr.pojo.EmployeeSalaryMerge;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.service.SobCfgService;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * 工资配置信息
 *
 * @author 🍍
 * @date 2023/10/1
 */
@RestController
@RequestMapping("/sal/sobcfg")
@Validated
public class SobCfgController {

    @Resource
    private SobCfgService sobCfgService;

    /**
     * 员工工资分页
     */
    @GetMapping("/{pageNum}/{pageSize}")
    @Log("查询员工工资分页")
    public RespBean findEmployeeSalaryPage(@Min(value = 1, message = "页码不能小于1")
                                           @PathVariable("pageNum") Integer pageNum,
                                           @Min(value = 1, message = "页大小不能小于1")
                                           @Max(value = 10, message = "页大小不能大于10")
                                           @PathVariable("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return RespBean.ok(new PageInfo<>(sobCfgService.selectEmpWithSalary()));
    }

    /**
     * 通过员工号查询
     */
    @GetMapping("/byWorkId/{workId}")
    @Log("通过员工号查询")
    public RespBean findEmployeeSalaryByWorkId(@Length(min = 8, max = 8, message = "员工号长度必须为8位")
                                               @PathVariable("workId") String workId) {
        return RespBean.ok(sobCfgService.selectEmployeeSalaryByWorkId(workId));
    }

    /**
     * 修改员工账套
     */
    @PutMapping("/modify/{employeeId}/{newSalaryId}")
    @Log("新增员工账套")
    public RespBean addSalaryWithEmployee(@Min(value = 1, message = "员工号不能小于1")
                                          @PathVariable("employeeId") Integer employeeId,
                                          @Min(value = 1, message = "新工资套号不能小于1")
                                          @PathVariable("newSalaryId") Integer newSalaryId) {
        EmployeeSalaryMerge employeeSalaryMerge = sobCfgService.selectEmployeeSalaryMergeByEmployeeId(employeeId);
        if (sobCfgService.addSalaryWithEmployee(employeeSalaryMerge, employeeId, newSalaryId)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }
}
