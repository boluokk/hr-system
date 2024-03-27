package org.boluo.hr.employee.controller;

import cn.hutool.core.util.ObjectUtil;
import org.bluo.global.pojo.RespBean;
import org.boluo.hr.employee.pojo.User;
import org.boluo.hr.employee.service.impl.LoginServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author boluo
 * @date 2023/11/08
 */
@RestController
public class LoginController {

    @Resource
    private LoginServiceImpl loginServiceImpl;

    @PostMapping("/login")
    public RespBean login(@RequestParam("workId") String workId,
                          @RequestParam("password") String password) {
        User user = loginServiceImpl.login(workId, password);
        if (ObjectUtil.isNotNull(user)) {
            return RespBean.ok(user);
        }
        return RespBean.error("账号不存在或者密码错误!");
    }
}
