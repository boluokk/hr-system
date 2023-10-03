package com.boluo.hr.controller.emp.adv;

import org.boluo.hr.pojo.RespBean;
import com.boluo.hr.service.AppraiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author @1352955539(boluo)
 * @date 2021/2/28 - 21:55
 */
@RestController
@RequestMapping("/emp/adv/app")
public class AppraiseController {

    private final AppraiseService appraiseService;

    @Autowired
    public AppraiseController(AppraiseService appraiseService) {
        this.appraiseService = appraiseService;
    }

    @GetMapping("/")
    public RespBean show() {
        return RespBean.ok(appraiseService.getAll());
    }
}
