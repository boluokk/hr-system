package org.boluo.hr.config.login_handle;

import org.boluo.hr.annotation.Log;
import org.boluo.hr.util.CheckUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 🍍
 * @date 2023/10/25
 */
@Component
public class CustomLogoutHandle implements LogoutHandler {
    @Override
    @Log("注销")
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        HttpSession session = request.getSession(false);
        if (CheckUtil.isNotNull(session)) {
            session.invalidate();
        }
    }
}
