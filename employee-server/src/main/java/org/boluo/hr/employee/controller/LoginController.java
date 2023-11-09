package org.boluo.hr.employee.controller;

import org.boluo.hr.pojo.RespBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author boluo
 * @date 2023/11/08
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @PostMapping()
    public RespBean login() {
        return RespBean.ok("登录成功", true);
    }
}
