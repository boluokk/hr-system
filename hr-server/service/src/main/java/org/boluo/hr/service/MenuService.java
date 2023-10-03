package com.boluo.hr.service;

import org.boluo.hr.mapper.MenuMapper;
import org.boluo.hr.pojo.Hr;
import org.boluo.hr.pojo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author @1352955539(boluo)
 * @date 2021/1/25 - 19:46
 */
@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    public List<Menu> getAllMenu() {
        return menuMapper.getAllMenusWithRole();
    }

    public List<Menu> getMenusByHrid() {
        return menuMapper.getMenusByHrId(((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }

}
