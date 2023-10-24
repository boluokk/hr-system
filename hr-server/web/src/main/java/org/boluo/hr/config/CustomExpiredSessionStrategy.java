package org.boluo.hr.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.boluo.hr.pojo.RespBean;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 🍍
 * @date 2023/10/23
 */
public class CustomExpiredSessionStrategy implements SessionInformationExpiredStrategy  {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException {
        HttpServletResponse response = event.getResponse();
        response.setStatus(402);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(new ObjectMapper().
                writeValueAsString(RespBean.error("异地登录, 请重新登录..")));
    }
}
