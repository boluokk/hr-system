package org.boluo.hr.controller.salary.sobcfg;

import org.boluo.hr.pojo.Employee;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.pojo.RespEmpBean;
import org.boluo.hr.service.SobCfgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/")
    public RespEmpBean findEmpWithSal(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        RespEmpBean rb = new RespEmpBean();
        List<Employee> list = sobCfgService.selectEmpWithSalary(pageNum, pageSize);
        int total = sobCfgService.countEmpWithSal();
        rb.setTotal(total);
        rb.setEmployees(list);
        return rb;
    }

    @GetMapping("/{empid}/{salid}")
    public RespBean addSalWithEmp(@PathVariable("empid") Integer eid, @PathVariable("salid") Integer salid) {
        try {
            sobCfgService.insertSalAndEmp(eid, salid);
            return RespBean.ok();
        } catch (Exception e) {
            return RespBean.error();
        }
    }
}
