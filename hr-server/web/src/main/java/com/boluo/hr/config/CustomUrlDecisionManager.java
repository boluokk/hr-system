package com.boluo.hr.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author @1352955539(boluo)
 * @date 2021/1/25 - 20:32
 */
@Component
public class CustomUrlDecisionManager implements AccessDecisionManager {

    //collection 角色
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws
            AccessDeniedException, InsufficientAuthenticationException {
        for (ConfigAttribute attribute: collection) {
            String needRole = attribute.getAttribute();
            if(needRole.equals("ROLE_LOGIN")) {
                if(authentication instanceof AnonymousAuthenticationToken) {
                    throw new AccessDeniedException("未登入，请登入");
                }else {
                    return;
                }
            }
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for(GrantedAuthority authority: authorities) {
                if(authority.getAuthority().equals(needRole)){
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不足");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
