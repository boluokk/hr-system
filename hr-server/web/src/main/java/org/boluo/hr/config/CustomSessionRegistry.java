package org.boluo.hr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.stereotype.Component;

/**
 * @author 🍍
 * @date 2023/10/27
 */
@Component
public class CustomSessionRegistry {
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
}
