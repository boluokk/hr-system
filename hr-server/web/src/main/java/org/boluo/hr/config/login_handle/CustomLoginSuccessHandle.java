package org.boluo.hr.config.login_handle;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.boluo.hr.annotation.Log;
import org.boluo.hr.pojo.Hr;
import org.bluo.global.pojo.RespBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 🍍
 * @date 2023/10/25
 */
@Component
public class CustomLoginSuccessHandle implements AuthenticationSuccessHandler {
    @Resource
    private SessionRegistry sessionRegistry;

    @Override
    @Log("登录")
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        Hr hr = (Hr) authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(hr, hr.getId(), hr.getAuthorities()));
        sessionRegistry.registerNewSession(request.getSession().getId(), hr);
        hr.setPassword(null);
        response.getWriter().write(new ObjectMapper().writeValueAsString(RespBean.ok("登入成功", hr)));
    }
}
