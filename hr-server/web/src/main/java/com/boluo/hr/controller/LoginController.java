package com.boluo.hr.controller;

import com.boluo.hr.pojo.RespBean;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author @1352955539(boluo)
 * @date 2021/1/31 - 22:14
 */
@RestController
public class LoginController {

    /**
    * @Description: Get请求方式的登录
    * @Param: []
    * @Return: com.boluo.hr.pojo.RespBean
    */
    @GetMapping(value = "/login")
    public RespBean login() {
        return RespBean.error("登入方式为POST！");
    }

}
