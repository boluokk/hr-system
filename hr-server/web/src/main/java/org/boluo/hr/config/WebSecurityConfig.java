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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * 授权和验证配置
 *
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

    @Autowired
    private CustomLogOutSuccessFilter customLogOutSuccessFilter;

    @Autowired
    private OperatorLogService operatorLogService;

    @Autowired
    private CustomLoginSuccessHandle customLoginSuccessHandle;

    @Autowired
    private CustomLogoutHandle customLogoutHandle;

    @Autowired
    private CustomExpiredSessionStrategy customExpiredSessionStrategy;

    @Autowired
    private CustomInvalidSessionStrategy customInvalidSessionStrategy;


    @Autowired
    public void customAuthenticationManager(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(hrService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        PasswordFilter passwordFilter = new PasswordFilter(authenticationManager(),
                operatorLogService, customLoginSuccessHandle);

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
                // 踢掉之前的登录 + 过期策略
                .sessionManagement().invalidSessionStrategy(customInvalidSessionStrategy)
                .maximumSessions(1).expiredSessionStrategy(customExpiredSessionStrategy);
    }
}
