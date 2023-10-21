package org.boluo.hr.controller.salary.sobcfg;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.service.SobCfgService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public RespBean findEmployeeWithSalary(@PathVariable("pageNum") Integer pageNum,
                                           @PathVariable("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return RespBean.ok(new PageInfo<>(sobCfgService.selectEmpWithSalary()));
    }

    /**
     * 新增员工工资记录
     */
    @PutMapping("/modify/{employeeId}/{salaryId}")
    public RespBean addSalaryWithEmployee(@PathVariable("employeeId") Integer employeeId,
                                          @PathVariable("salaryId") Integer salaryId) {
        try {
            sobCfgService.updateEmployeeSalary(employeeId, salaryId);
            return RespBean.ok();
        } catch (Exception e) {
            return RespBean.error();
        }
    }
}
