package org.boluo.hr.controller;

import org.boluo.hr.pojo.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 🍍
 * @date 2023/10/1
 */
@RestController
public class LoginController {

    @GetMapping( "/login")
    public RespBean login() {
        return RespBean.error("登入方式为POST！");
    }

}
