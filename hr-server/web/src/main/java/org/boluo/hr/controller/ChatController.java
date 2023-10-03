package com.boluo.hr.controller;

import org.boluo.hr.pojo.RespBean;
import com.boluo.hr.service.HrService;
import com.boluo.hr.service.util.HrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author @1352955539(boluo)
 * @date 2021/2/22 - 20:58
 */
@RestController
@RequestMapping("/chat")
public class ChatController {

    private HrService hrService;

    @Autowired
    public ChatController(HrService hrService) {
        this.hrService = hrService;
    }

    @GetMapping("/hrs")
    public RespBean getAllExceptCurrentHr() {
        return RespBean.ok(hrService.getAllExceptCurrentHr(HrUtils.getCurrentHr().getId()));
    }

}
