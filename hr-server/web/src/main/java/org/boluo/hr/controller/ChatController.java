package org.boluo.hr.controller;

import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.service.HrService;
import org.boluo.hr.service.util.HrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 🍍
 * @date 2023/10/1
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
