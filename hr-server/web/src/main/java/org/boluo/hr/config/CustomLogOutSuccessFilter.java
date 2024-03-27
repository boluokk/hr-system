package org.boluo.hr.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bluo.global.pojo.RespBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 🍍
 * @date 2023/10/25
 */
@Component
public class CustomLogOutSuccessFilter implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse res, Authentication authentication)
            throws IOException {
        res.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        res.getWriter().print(new ObjectMapper().writeValueAsString(RespBean.ok("注销成功")));
    }
}
