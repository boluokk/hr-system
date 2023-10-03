package com.boluo.hr.controller;

import org.boluo.hr.pojo.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author @1352955539(boluo)
 * @date 2021/1/31 - 22:14
 */
@RestController
public class LoginController {

    @GetMapping( "/login")
    public RespBean login() {
        return RespBean.error("登入方式为POST！");
    }

}
