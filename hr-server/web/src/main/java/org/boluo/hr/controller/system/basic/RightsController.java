package org.boluo.hr.controller.system.basic;

import org.boluo.hr.annotation.Log;
import org.boluo.hr.pojo.InsertRole;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.pojo.UploadRole;
import org.boluo.hr.pojo.UploadRoleMenu;
import org.boluo.hr.service.MenuService;
import org.boluo.hr.service.RightsService;
import org.boluo.hr.util.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Set;


/**
 * 权限信息
 *
 * @author 🍍
 * @date 2023/10/1
 */
@RestController
@RequestMapping("/system/basic/rights")
@Validated
public class RightsController {

    @Resource
    private RightsService rightsService;


    /**
     * 角色前缀
     */
    private static final String PRE_ROLE_NAME = "ROLE_";

    @Autowired
    public RightsController(RightsService rightsService) {
        this.rightsService = rightsService;
    }

    /**
     * 返回所有角色
     */
    @GetMapping("/")
    @Log("查询所有角色")
    public RespBean findAllRoles() {
        return RespBean.ok(rightsService.selectAllRoles());
    }

    /**
     * 返回角色权限
     */
    @GetMapping("/{roleId}")
    @Log("查询角色权限")
    public RespBean findRightByRoleId(@Min(value = 1, message = "角色ID必须大于0")
                                      @PathVariable("roleId") Integer roleId) {
        return RespBean.ok(rightsService.selectRightsByRoleId(roleId));
    }

    /**
     * 返回所有菜单
     */
    @GetMapping("/menus")
    @Log("查询所有菜单")
    public RespBean findAllMenus() {
        return RespBean.ok(rightsService.selectAllMenus());
    }

    /**
     * 新增角色
     */
    @PutMapping("/add")
    @Log("新增角色")
    public RespBean addRole(@Valid @RequestBody InsertRole insertRole) {
        insertRole.setName(PRE_ROLE_NAME + insertRole.getName());
        if (!CheckUtil.isNull(rightsService.selectRoleByName(insertRole.getName()))) {
            return RespBean.error("当前角色英文名已经存在");
        }
        if (rightsService.insertRole(insertRole)) {
            return RespBean.ok();
        } else {
            return RespBean.error();
        }
    }

    /**
     * 删除角色
     */
    @DeleteMapping("/delete/{roleId}")
    @Log("删除角色")
    public RespBean remove(@Min(value = 1, message = "角色ID必须大于0")
                           @PathVariable("roleId") Integer roleId) {
        if (rightsService.deleteRoleById(roleId)) {
            return RespBean.ok();
        } else {
            return RespBean.error();
        }
    }

    /**
     * 修改角色
     */
    @PutMapping("/modify")
    @Log("修改角色")
    public RespBean modifyRole(@Valid @RequestBody UploadRole uploadRole) {
        if (rightsService.updateRole(uploadRole)) {
            return RespBean.ok();
        } else {
            return RespBean.error();
        }
    }

    /**
     * 修改权限
     */
    @PutMapping("/modify/rights")
    @Log("修改权限")
    public RespBean modifyRights(@Valid @RequestBody UploadRoleMenu uploadRoleMenu) {
        if (CheckUtil.hasLength(uploadRoleMenu.getMenuIds())) {
            Set<Integer> menuIds = rightsService.selectAllMenuIds();
            for (Integer menuId : uploadRoleMenu.getMenuIds()) {
                if (!menuIds.contains(menuId)) {
                    return RespBean.error("不存在的菜单ID: " + menuId);
                }
            }
        }
        if (rightsService.modifyRights(uploadRoleMenu)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }
}
