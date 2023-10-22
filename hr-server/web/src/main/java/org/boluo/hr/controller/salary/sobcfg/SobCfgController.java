package org.boluo.hr.controller.salary.sobcfg;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.boluo.hr.pojo.EmployeeSalaryMerge;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.service.SobCfgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * 工资配置信息
 *
 * @author 🍍
 * @date 2023/10/1
 */
@RestController
@RequestMapping("/sal/sobcfg")
public class SobCfgController {

    private final SobCfgService sobCfgService;

    @Autowired
    public SobCfgController(SobCfgService sobCfgService) {
        this.sobCfgService = sobCfgService;
    }

    /**
     * 员工工资分页
     */
    @GetMapping("/{pageNum}/{pageSize}")
    public RespBean findEmployeeSalaryPage(@PathVariable("pageNum") Integer pageNum,
                                           @PathVariable("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return RespBean.ok(new PageInfo<>(sobCfgService.selectEmpWithSalary()));
    }

    /**
     * 通过员工号查询
     */
    @GetMapping("/byWorkId/{workId}")
    public RespBean findEmployeeSalaryByWorkId(@PathVariable("workId") String workId) {
        return RespBean.ok(sobCfgService.selectEmployeeSalaryByWorkId(workId));
    }

    /**
     * 新增员工账套
     */
    @PutMapping("/modify/{employeeId}/{newSalaryId}")
    @Transactional(rollbackFor = Exception.class)
    public RespBean addSalaryWithEmployee(@PathVariable("employeeId") Integer employeeId,
                                          @PathVariable("newSalaryId") Integer newSalaryId) {
        EmployeeSalaryMerge employeeSalaryMerge = sobCfgService.selectEmployeeSalaryMergeByEmployeeId(employeeId);
        // 没有就新增
        if (employeeSalaryMerge == null) {
            if (sobCfgService.insertEmployeeSalaryMerge(
                    new EmployeeSalaryMerge().setEmployeeId(employeeId).setSalaryId(newSalaryId))) {
                return RespBean.ok();
            }
            return RespBean.error("员工账套不存在, 并且新增失败");
        } else {
            if (sobCfgService.updateEmployeeSalary(employeeSalaryMerge.setSalaryId(newSalaryId))) {
                return RespBean.ok();
            }
            return RespBean.error("更新失败");
        }
    }
}
