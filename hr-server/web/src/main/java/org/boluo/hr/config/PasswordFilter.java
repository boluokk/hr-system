package org.boluo.hr.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.boluo.hr.pojo.LoginUser;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 🍍
 * @date 2023/10/23
 */
public class PasswordFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        if (!"POST".equalsIgnoreCase(request.getMethod())) {
            throw new AuthenticationServiceException("不支持的请求类型");
        }
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            try {
                LoginUser loginUser = new ObjectMapper().readValue(request.getInputStream(), LoginUser.class);
                if (!StringUtils.hasLength(loginUser.getUsername())) {
                    throw new AuthenticationServiceException("用户名不能为空");
                }
                if (!StringUtils.hasLength(loginUser.getPassword())) {
                    throw new AuthenticationServiceException("密码不能为空");
                }
                UsernamePasswordAuthenticationToken authRequest =
                        UsernamePasswordAuthenticationToken.unauthenticated(
                                loginUser.getUsername(),
                                loginUser.getPassword());
                this.setDetails(request, authRequest);
                return this.getAuthenticationManager().authenticate(authRequest);
            } catch (IOException e) {
                throw new AuthenticationServiceException("账户名称格式不正确");
            }
        } else {
            return super.attemptAuthentication(request, response);
        }
    }
}
