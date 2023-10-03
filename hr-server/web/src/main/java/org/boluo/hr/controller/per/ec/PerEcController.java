package org.boluo.hr.controller.per.ec;

import org.boluo.hr.pojo.Employeeec;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.service.EmployeecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 🍍
 * @date 2023/10/1
 */
@RestController
@RequestMapping("/per/ec")
public class PerEcController {

    private final EmployeecService employeecService;

    @Autowired
    public PerEcController(EmployeecService employeecService) {
        this.employeecService = employeecService;
    }

    @GetMapping("/")
    public RespBean findAll() {
        return RespBean.ok(employeecService.selectAll());
    }

    @PutMapping("/change")
    public RespBean modifyOne(Employeeec employeeec) {
        if (employeecService.update(employeeec)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    @DeleteMapping("/del/{id}")
    public RespBean removeOne(@PathVariable("id") Integer id) {
        if (employeecService.delete(id)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    @PutMapping("/add/one")
    public RespBean addOne(Employeeec employeeec) {
        if (employeecService.insert(employeeec)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }
}
