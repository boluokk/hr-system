package org.boluo.hr.controller;

import org.boluo.hr.annotation.Log;
import org.boluo.hr.pojo.Menu;
import org.boluo.hr.service.MenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单信息
 *
 * @author 🍍
 * @date 2023/10/1
 */
@RestController
@RequestMapping("/syscon")
public class MenuController {

    @Resource
    private MenuService menuService;


    /**
     * 获取菜单信息(当前已经登录用户的)
     */
    @GetMapping("/menu")
    @Log("查询当前用户的菜单")
    public List<Menu> getRoleWithMenus() {
        return menuService.selectMenusByHrId();
    }
}
