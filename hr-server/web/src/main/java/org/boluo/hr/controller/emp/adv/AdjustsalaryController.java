package org.boluo.hr.controller.emp.adv;

import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.service.AdjustsalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 🍍
 * @date 2023/10/1
 */
@RestController
@RequestMapping("/emp/adv/adj")
public class AdjustsalaryController {

    private final AdjustsalaryService adjustsalaryService;

    @Autowired
    public AdjustsalaryController(AdjustsalaryService adjustsalaryService) {
        this.adjustsalaryService = adjustsalaryService;
    }

    @GetMapping("/")
    public RespBean show() {
        return RespBean.ok(adjustsalaryService.selectAll());
    }
}
