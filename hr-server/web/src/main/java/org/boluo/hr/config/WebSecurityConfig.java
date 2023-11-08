package org.boluo.hr.config;

import org.boluo.hr.config.login_handle.CustomLoginSuccessHandle;
import org.boluo.hr.config.login_handle.CustomLogoutHandle;
import org.boluo.hr.service.HrService;
import org.boluo.hr.service.OperatorLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.annotation.Resource;

/**
 * 授权和验证配置
 *
 * @author 🍍
 * @date 2023/10/1
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private HrService hrService;

    @Resource
    private CustomUrlDecisionManager customUrlDecisionManager;

    @Resource
    private CustomFilterInvocationSecurityMetadataSource customFilterInvocationSecurityMetadataSource;

    @Resource
    private CustomLogOutSuccessFilter customLogOutSuccessFilter;

    @Resource
    private OperatorLogService operatorLogService;

    @Resource
    private CustomLoginSuccessHandle customLoginSuccessHandle;

    @Resource
    private CustomLogoutHandle customLogoutHandle;

    @Resource
    private SessionRegistry sessionRegistry;

    @Resource
    private CustomExpiredSessionStrategy customExpiredSessionStrategy;

    @Autowired
    public void customAuthenticationManager(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(hrService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        PasswordFilter passwordFilter = new PasswordFilter(authenticationManager(),
                operatorLogService, customLoginSuccessHandle, sessionRegistry);
        http.addFilter(passwordFilter);
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
                .addLogoutHandler(customLogoutHandle)
                .logoutSuccessHandler(customLogOutSuccessFilter)
                .and().exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .and().csrf().disable()
                .sessionManagement().maximumSessions(1).expiredSessionStrategy(customExpiredSessionStrategy)
                .sessionRegistry(sessionRegistry);
    }
}
