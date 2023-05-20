package com.boluo.hr.controller.emp.adv;

import com.boluo.hr.pojo.RespBean;
import com.boluo.hr.pojo.SalaryMonth;
import com.boluo.hr.service.SalarymontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author @1352955539(boluo)
 * @date 2021/3/28 - 12:37
 */

@RestController
@RequestMapping("/emp/adv/salaryInfo")
public class SalaryInfoController {

    @Autowired
    private SalarymontService salarymontService;

    @GetMapping("/")
    public RespBean show() {
        List<SalaryMonth> salaryMonths = salarymontService.SearchAll();
        return RespBean.ok(salaryMonths);
    }
}
