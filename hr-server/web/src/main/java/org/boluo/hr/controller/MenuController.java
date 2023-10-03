package org.boluo.hr.controller;

import org.boluo.hr.pojo.Menu;
import org.boluo.hr.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 🍍
 * @date 2023/10/1
 */
@Controller
@ResponseBody
@RequestMapping("/syscon")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/menu")
    public List<Menu> getRoleWithMenus() {
        return menuService.selectMenusByHrId();
    }
}
