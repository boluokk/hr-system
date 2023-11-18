package org.boluo.hr.service;

import org.boluo.hr.mapper.MenuMapper;
import org.boluo.hr.pojo.Hr;
import org.boluo.hr.pojo.Menu;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单 业务层
 *
 * @author 🍍
 * @date 2023/10/1
 */
@Service
public class MenuService {

    @Resource
    private MenuMapper menuMapper;

    /**
     * 查询所有带角色的菜单
     *
     * @return 带角色菜单集合
     */
    public List<Menu> selectAllMenu() {
        return menuMapper.selectAllMenusWithRole();
    }

    /**
     * 查询人事拥有的菜单
     *
     * @return 菜单集合
     */
    public List<Menu> selectMenusByHrId() {
        return menuMapper.selectMenusByHrId(((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }

}
