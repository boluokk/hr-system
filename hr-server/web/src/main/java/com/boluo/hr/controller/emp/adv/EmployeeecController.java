package com.boluo.hr.controller.emp.adv;

import com.boluo.hr.pojo.Employeeec;
import com.boluo.hr.pojo.RespBean;
import com.boluo.hr.service.EmployeecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author @1352955539(boluo)
 * @date 2021/2/28 - 21:27
 */
@RestController
@RequestMapping("/emp/adv/c")
public class EmployeeecController {

    @Autowired
    private EmployeecService employeecService;

    @GetMapping("/")
    public RespBean show() {
        return RespBean.ok(employeecService.getAll());
    }
}
