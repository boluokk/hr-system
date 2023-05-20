package com.boluo.hr.controller;

import com.boluo.hr.pojo.Menu;
import com.boluo.hr.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author @1352955539(boluo)
 * @date 2021/1/28 - 18:22
 */
@Controller
@ResponseBody
@RequestMapping("/syscon")
public class MenuController {

    @Autowired
    MenuService menuService;

    /**
     * @Description: 获取所有权限菜单
     * @Param: []
     * @Return: java.util.List<com.boluo.hr.pojo.Menu>
     */
    @GetMapping("/menu")
    public List<Menu> getRoleWithMenus() {
        return menuService.getMenusByHrid();
    }
}
