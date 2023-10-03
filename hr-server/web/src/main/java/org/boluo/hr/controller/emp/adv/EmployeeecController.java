package org.boluo.hr.controller.emp.adv;

import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.service.EmployeecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 🍍
 * @date 2023/10/1
 */

// 奖惩控制
@RestController
@RequestMapping("/emp/adv/c")
public class EmployeeecController {


    private final EmployeecService employeecService;

    @Autowired
    public EmployeeecController(EmployeecService employeecService) {
        this.employeecService = employeecService;
    }

    @GetMapping("/")
    public RespBean show() {
        return RespBean.ok(employeecService.selectAll());
    }
}
