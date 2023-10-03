package org.boluo.hr.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.boluo.hr.pojo.Hr;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 🍍
 * @date 2023/10/1
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    HrService hrService;

    @Autowired
    CustomUrlDecisionManager customUrlDecisionManager;

    @Autowired
    CustomFilterInvocationSecurityMetadataSource customFilterInvocationSecurityMetadataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(hrService).passwordEncoder(new BCryptPasswordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(customUrlDecisionManager);
                        o.setSecurityMetadataSource(customFilterInvocationSecurityMetadataSource);
                        return o;
                    }
                })
                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/login")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication authentication) throws IOException, ServletException {
                        res.setContentType("application/json;charset=utf-8");
                        Hr hr = (Hr) authentication.getPrincipal();
                        hr.setPassword(null);
                        res.getWriter().write(new ObjectMapper().writeValueAsString(RespBean.ok("登入成功", hr)));
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) throws IOException, ServletException {
                        res.setContentType("application/json;charset=utf-8");
                        RespBean respBean = RespBean.error("登入失败!");
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
                }).permitAll()
                .and()
                .logout()
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse res, Authentication authentication) throws IOException {
                        res.setContentType("application/json;charset=utf-8");
                        res.getWriter().print(new ObjectMapper().writeValueAsString(RespBean.ok("注销成功")));
                    }
                })
                .and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) throws IOException, ServletException {
                        res.setContentType("application/json;charset=utf-8");
                        res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                        RespBean respBean = RespBean.error("登入失败!");
                        if (e instanceof InsufficientAuthenticationException) {
                            res.setStatus(401);
                            respBean.setMsg("登入失败,请联系管理员!");
                        }
                        res.getWriter().print(new ObjectMapper().writeValueAsString(respBean));
                    }
                });
    }
}
