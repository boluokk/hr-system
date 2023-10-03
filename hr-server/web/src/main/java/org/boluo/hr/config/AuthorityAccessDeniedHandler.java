package com.boluo.hr.config;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author @1352955539(boluo)
 * @date 2021/1/25 - 22:39
 */

@Component
public class AuthorityAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException e) throws IOException {
        res.setStatus(HttpServletResponse.SC_FORBIDDEN);
        res.setCharacterEncoding("UTF-8");
        PrintWriter writer = res.getWriter();
        writer.write("{\"status\":\"error\",\"msg\":\"权限不足，请联系管理员!\"}");
        writer.flush();
        writer.close();
    }
}
