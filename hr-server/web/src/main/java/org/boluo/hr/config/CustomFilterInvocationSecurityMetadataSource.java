package org.boluo.hr.config;

import org.bluo.common.redis.util.RedisCache;
import org.bluo.common.redis.util.RedisConstants;
import org.boluo.hr.pojo.AllMenu;
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
import java.util.concurrent.TimeUnit;

/**
 * @author 🍍
 * @date 2023/10/1
 */
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private final RedisCache redisCache;
    private final MenuService menuService;

    @Autowired
    public CustomFilterInvocationSecurityMetadataSource(RedisCache redisCache,
                                                        MenuService menuService) {
        this.redisCache = redisCache;
        this.menuService = menuService;
    }

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 通过对请求url的判别出需要哪些权限
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();

        if (antPathMatcher.match("/sal/table/**", requestUrl)) {
            return SecurityConfig.createList("ROLE_FEIGN");
        }

        // 存入 redis
        AllMenu allMenu = redisCache.getWithPassThrough(RedisConstants.All_MENU, "",
                AllMenu.class, id -> new AllMenu().setAllMenu(menuService.selectAllMenu()),
                5L, TimeUnit.MINUTES);
        for (Menu menu : allMenu.getAllMenu()) {
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
