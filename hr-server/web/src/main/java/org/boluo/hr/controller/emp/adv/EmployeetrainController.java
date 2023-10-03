package com.boluo.hr.controller.emp.adv;

import org.boluo.hr.pojo.RespBean;
import com.boluo.hr.service.EmployeetrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author @1352955539(boluo)
 * @date 2021/2/28 - 21:52
 */
@RestController
@RequestMapping("/emp/adv/train")
public class EmployeetrainController {

    private final EmployeetrainService employeetrainService;

    @Autowired
    public EmployeetrainController(EmployeetrainService employeetrainService) {
        this.employeetrainService = employeetrainService;
    }

    @GetMapping("/")
    public RespBean show() {
        return RespBean.ok(employeetrainService.getAll());
    }
}
