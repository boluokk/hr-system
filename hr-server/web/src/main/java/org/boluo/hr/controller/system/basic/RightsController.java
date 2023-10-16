package org.boluo.hr.controller.system.basic;

import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.pojo.RightsBean;
import org.boluo.hr.pojo.Role;
import org.boluo.hr.service.RightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 权限信息
 *
 * @author 🍍
 * @date 2023/10/1
 */
@RestController
@RequestMapping("/system/basic/rights")
public class RightsController {

    private final RightsService rightsService;

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
     *
     * @return
     */
    @GetMapping("/")
    public RespBean findAllRoles() {
        return RespBean.ok(rightsService.selectAllRoles());
    }

    /**
     * 返回角色权限
     */
    @GetMapping("/{roleId}")
    public RespBean findRightByRoleId(@PathVariable("roleId") Integer roleId) {
        return RespBean.ok(rightsService.selectRightsByRoleId(roleId));
    }

    /**
     * 返回所有菜单
     */
    @GetMapping("/menus")
    public RespBean findAllMenus() {
        return RespBean.ok(rightsService.selectAllMenus());
    }

    /**
     * 新增角色
     */
    @PutMapping("/add")
    public RespBean addRole(Role role) {
        role.setName(PRE_ROLE_NAME + role.getName());
        if (rightsService.insertRole(role)) {
            return RespBean.ok();
        } else {
            return RespBean.error();
        }
    }

    /**
     * 删除角色
     */
    @DeleteMapping("/delete/{roleId}")
    public RespBean remove(@PathVariable("roleId") Integer roleId) {
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
    public RespBean modifyRole(Role role) {
        if (rightsService.updateRole(role)) {
            return RespBean.ok();
        } else {
            return RespBean.error();
        }
    }

    /**
     * 修改权限
     */
    @PutMapping("/reRights/{roleId}")
    public RespBean modifyRights(@PathVariable("roleId") Integer roleId, Integer[] ids) {
        int countRight = rightsService.selectRightsCountByRoleId(roleId);
        if (countRight > 0) {
            if (rightsService.deleteRights(roleId)) {
                if (ids == null) {
                    return RespBean.ok();
                }
                if (rightsService.insertRights(new RightsBean(ids, roleId))) {
                    return RespBean.ok();
                } else {
                    return RespBean.error();
                }
            } else {
                return RespBean.error();
            }
        } else {
            if (ids == null) {
                return RespBean.ok();
            }
            if (rightsService.insertRights(new RightsBean(ids, roleId))) {
                return RespBean.ok();
            } else {
                return RespBean.error();
            }
        }
    }
}
