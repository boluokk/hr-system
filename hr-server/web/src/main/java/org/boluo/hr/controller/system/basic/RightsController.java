package org.boluo.hr.controller.system.basic;

import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.pojo.RightsBean;
import org.boluo.hr.pojo.Role;
import org.boluo.hr.pojo.UploadRoleMenu;
import org.boluo.hr.service.RightsService;
import org.boluo.hr.util.CheckUtil;
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
    public RespBean addRole(@RequestBody Role role) {
        role.setName(PRE_ROLE_NAME + role.getName());
        if (!CheckUtil.isNull(rightsService.selectRoleByName(role.getName()))) {
            return RespBean.error("当前角色英文名已经存在");
        }
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
    public RespBean modifyRole(@RequestBody Role role) {
        if (rightsService.updateRole(role)) {
            return RespBean.ok();
        } else {
            return RespBean.error();
        }
    }

    /**
     * 修改权限
     */
    @PutMapping("/modify/rights")
    public RespBean modifyRights(@RequestBody UploadRoleMenu uploadRoleMenu) {
        int countRight = rightsService.selectRightsCountByRoleId(uploadRoleMenu.getRoleId());
        if (countRight > 0) {
            if (rightsService.deleteRights(uploadRoleMenu.getRoleId())) {
                if (!CheckUtil.hasLength(uploadRoleMenu.getMenuIds())) {
                    return RespBean.ok();
                }
                if (rightsService.insertRights(new RightsBean(uploadRoleMenu.getMenuIds(),
                        uploadRoleMenu.getRoleId()))) {
                    return RespBean.ok();
                } else {
                    return RespBean.error();
                }
            } else {
                return RespBean.error();
            }
        } else {
            if (!CheckUtil.hasLength(uploadRoleMenu.getMenuIds())) {
                return RespBean.ok();
            }
            if (rightsService.insertRights(new RightsBean(uploadRoleMenu.getMenuIds(), uploadRoleMenu.getRoleId()))) {
                return RespBean.ok();
            } else {
                return RespBean.error();
            }
        }
    }
}
