package org.boluo.hr.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.boluo.hr.annotation.Log;
import org.boluo.hr.config.login_handle.CustomLoginSuccessHandle;
import org.boluo.hr.pojo.LoginUser;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.service.OperatorLogService;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 🍍
 * @date 2023/10/23
 */

@Slf4j
public class PasswordFilter extends UsernamePasswordAuthenticationFilter {

    private final OperatorLogService operatorLogService;

    public PasswordFilter(AuthenticationManager manager,
                          OperatorLogService operatorLogService,
                          CustomLoginSuccessHandle customLoginSuccessHandle) {
        this.operatorLogService = operatorLogService;
        this.setFilterProcessesUrl("/login");
        this.setAuthenticationManager(manager);
        this.setUsernameParameter("username");
        this.setPasswordParameter("password");
        this.setAuthenticationSuccessHandler(customLoginSuccessHandle);
        this.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest req,
                                                HttpServletResponse res,
                                                AuthenticationException e) throws IOException {
                res.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                RespBean respBean = RespBean.error(e.getMessage());
                if (e instanceof LockedException) {
                    respBean.setMsg("账户被锁定，请联系管理员！");
                } else if (e instanceof DisabledException) {
                    respBean.setMsg("账户不可用，请联系管理员！");
                } else if (e instanceof CredentialsExpiredException) {
                    respBean.setMsg("密码过期，请联系管理员！");
                } else if (e instanceof AccountExpiredException) {
                    respBean.setMsg("账户过期，请联系管理员！");
                } else if (e instanceof BadCredentialsException) {
                    respBean.setMsg("账户或密码错误，请联系管理员！");
                } else if (e instanceof InternalAuthenticationServiceException) {
                    respBean.setMsg("账户或密码错误，请联系管理员！");
                }
                res.getWriter().write(new ObjectMapper().writeValueAsString(respBean));
            }
        });
    }

    @Override
    @Log("登录")
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        if (!"POST".equalsIgnoreCase(request.getMethod())) {
            throw new AuthenticationServiceException("不支持的请求类型");
        }
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE)) {
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
            throw new AuthenticationServiceException("请求类型不匹配");
        }
    }
}
