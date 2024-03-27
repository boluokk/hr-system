package org.boluo.hr.controller;

import org.bluo.global.pojo.RespBean;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author boluo
 * @date 2023/11/10
 */
@RestController
public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public RespBean error() {
        return new RespBean(HttpStatus.BAD_REQUEST.value(), "请求路径错误");
    }
}
