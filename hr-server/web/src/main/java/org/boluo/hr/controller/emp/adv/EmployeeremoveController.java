package com.boluo.hr.controller.emp.adv;

import org.boluo.hr.pojo.RespBean;
import com.boluo.hr.service.EmployeeremoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author @1352955539(boluo)
 * @date 2021/2/28 - 21:57
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
    public RespBean show() {
        return RespBean.ok(employeeremoveService.getAll());
    }
}
