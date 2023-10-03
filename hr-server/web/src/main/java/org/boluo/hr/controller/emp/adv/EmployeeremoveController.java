package org.boluo.hr.controller.emp.adv;

import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.service.EmployeeremoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 🍍
 * @date 2023/10/1
 */
@RestController
@RequestMapping("/emp/adv/remove")
public class EmployeeremoveController {

    private final EmployeeremoveService employeeremoveService;

    @Autowired
    public EmployeeremoveController(EmployeeremoveService employeeremoveService) {
        this.employeeremoveService = employeeremoveService;
    }

    @GetMapping("/")
    public RespBean findAll() {
        return RespBean.ok(employeeremoveService.selectAll());
    }
}
