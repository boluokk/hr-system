package org.boluo.hr.controller;

import org.boluo.hr.annotation.Log;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.service.HrService;
import org.boluo.hr.util.HrUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 聊天信息
 *
 * @author 🍍
 * @date 2023/10/1
 */
@RestController
@RequestMapping("/chat")
public class ChatController {

    @Resource
    private HrService hrService;

    /**
     * 获取当前人事信息
     */
    @GetMapping("/hrs")
    @Log("获取当前人事信息")
    public RespBean getAllExceptCurrentHr() {
        return RespBean.ok(hrService.selectAllExceptCurrentHr(HrUtils.getCurrentHr().getId()));
    }

}
