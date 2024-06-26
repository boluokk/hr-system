package org.boluo.hr.controller;

import org.boluo.hr.annotation.Log;
import org.bluo.global.pojo.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录
 *
 * @author 🍍
 * @date 2023/10/1
 */
@RestController
public class LoginController {

    /**
     * 登录(应为POST格式)
     */
    @GetMapping( "/login")
    @Log("GET方式的登录")
    public RespBean login() {
        return RespBean.error("登入方式为POST！");
    }

}
