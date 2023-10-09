package org.boluo.hr.controller.salary.sobcfg;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.boluo.hr.pojo.Employee;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.pojo.RespEmpBean;
import org.boluo.hr.service.SobCfgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
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

    @GetMapping("/{pageNum}/{pageSize}")
    public RespBean findEmployeeWithSalary(@PathVariable("pageNum") Integer pageNum,
                                              @PathVariable("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return RespBean.ok(new PageInfo<>(sobCfgService.selectEmpWithSalary()));
    }

    @GetMapping("/{empId}/{salId}")
    public RespBean addSalWithEmp(@PathVariable("empId") Integer empId, @PathVariable("salId") Integer salId) {
        try {
            sobCfgService.insertSalAndEmp(empId, salId);
            return RespBean.ok();
        } catch (Exception e) {
            return RespBean.error();
        }
    }
}
