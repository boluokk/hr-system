package org.boluo.hr.controller.emp.adv;

import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.service.SalarymontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 🍍
 * @date 2023/10/1
 */
@RestController
@RequestMapping("/emp/adv/salaryInfo")
public class SalaryInfoController {

    private final SalarymontService salarymontService;

    @Autowired
    public SalaryInfoController(SalarymontService salarymontService) {
        this.salarymontService = salarymontService;
    }

    @GetMapping("/")
    public RespBean show() {
        return RespBean.ok(salarymontService.selectAll());
    }
}
