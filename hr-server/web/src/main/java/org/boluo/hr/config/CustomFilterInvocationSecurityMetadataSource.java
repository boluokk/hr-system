package org.boluo.hr.config;

import org.boluo.hr.pojo.Menu;
import org.boluo.hr.pojo.Role;
import org.boluo.hr.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @author 🍍
 * @date 2023/10/1
 */
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private final MenuService menuService;

    @Autowired
    public CustomFilterInvocationSecurityMetadataSource(MenuService menuService) {
        this.menuService = menuService;
    }

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    // 通过对请求url的判别出需要哪些角色权限
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        List<Menu> allMenu = menuService.selectAllMenu();
        for (Menu menu : allMenu) {
            if (antPathMatcher.match(menu.getUrl(), requestUrl)) {
                List<Role> roles = menu.getRoles();
                String[] strRoles = new String[roles.size()];
                for (int i = 0; i < roles.size(); i++) {
                    strRoles[i] = roles.get(i).getName();
                }
                return SecurityConfig.createList(strRoles);
            }
        }
         return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
