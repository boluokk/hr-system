package com.boluo.hr.controller;

import com.boluo.hr.pojo.Hr;
import com.boluo.hr.pojo.RespBean;
import com.boluo.hr.service.HrService;
import com.boluo.hr.service.util.HrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author @1352955539(boluo)
 * @date 2021/2/22 - 20:58
 */
@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private HrService hrService;

    /**
    * @Description: 获取除当前hr的所有hr信息
    * @Param: []
    * @Return: com.boluo.hr.pojo.RespBean
    */
    @GetMapping("/hrs")
    public RespBean getAllExceptCurrentHr() {
        List<Hr> list = hrService.getAllExceptCurrentHr(HrUtils.getCurrentHr().getId());
        return RespBean.ok(list);
    }

}
