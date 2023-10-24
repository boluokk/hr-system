package org.boluo.hr.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.boluo.hr.pojo.Hr;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

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
    private HrService hrService;

    @Autowired
    private CustomUrlDecisionManager customUrlDecisionManager;

    @Autowired
    private CustomFilterInvocationSecurityMetadataSource customFilterInvocationSecurityMetadataSource;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(hrService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(passwordFilter(), UsernamePasswordAuthenticationFilter.class);
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(customUrlDecisionManager);
                        o.setSecurityMetadataSource(customFilterInvocationSecurityMetadataSource);
                        return o;
                    }
                })
                .and()
                .logout()
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse res, Authentication authentication) throws IOException {
                        res.setContentType("application/json;charset=utf-8");
                        res.getWriter().print(new ObjectMapper().writeValueAsString(RespBean.ok("注销成功")));
                    }
                })
                .and().exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .and().csrf().disable()
                // 踢掉之前的登录 + 过期策略
                .sessionManagement().maximumSessions(1).expiredSessionStrategy(new CustomExpiredSessionStrategy());
    }

    @Bean
    public AuthenticationManager authenticationManagerBean()
            throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordFilter passwordFilter() throws Exception {
        PasswordFilter passwordFilter = new PasswordFilter();
        passwordFilter.setFilterProcessesUrl("/login");
        passwordFilter.setAuthenticationManager(authenticationManagerBean());
        passwordFilter.setUsernameParameter("username");
        passwordFilter.setPasswordParameter("password");
        passwordFilter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                Authentication authentication) throws IOException {
                response.setContentType("application/json;charset=utf-8");
                Hr hr = (Hr) authentication.getPrincipal();
                hr.setPassword(null);
                response.getWriter().write(new ObjectMapper().writeValueAsString(RespBean.ok("登入成功", hr)));
            }
        });
        passwordFilter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest req,
                                                HttpServletResponse res,
                                                AuthenticationException e) throws IOException {
                res.setContentType("application/json;charset=utf-8");
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
        return passwordFilter;
    }
}
