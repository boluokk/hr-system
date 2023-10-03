package org.boluo.hr.controller.system.basic;

import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.pojo.RightsBean;
import org.boluo.hr.pojo.Role;
import org.boluo.hr.service.RightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author 🍍
 * @date 2023/10/1
 */
@RestController
@RequestMapping("/system/basic/rights")
public class RightsController {

    private final RightsService rightsService;

    private static final String PRE_ROLE_NAME = "ROLE_";

    @Autowired
    public RightsController(RightsService rightsService) {
        this.rightsService = rightsService;
    }

    @GetMapping("/")
    public RespBean findAllRoles() {
        return RespBean.ok(rightsService.selectAllRoles());
    }

    @GetMapping("/{id}")
    public RespBean findRightByRoleId(@PathVariable Integer id) {
        return RespBean.ok(rightsService.selectRightsByRoleId(id));
    }

    @GetMapping("/menus")
    public RespBean findAllMenus() {
        return RespBean.ok(rightsService.selectAllMenus());
    }

    @PutMapping("/")
    public RespBean addRole(Role role) {
        role.setName(PRE_ROLE_NAME + role.getName());
        if (rightsService.insertRole(role)) {
            return RespBean.ok();
        } else {
            return RespBean.error();
        }
    }

    @DeleteMapping("/{id}")
    public RespBean remove(@PathVariable Integer id) {
        if (rightsService.deleteRoleById(id)) {
            return RespBean.ok();
        } else {
            return RespBean.error();
        }
    }

    @PutMapping("/changeRoleName")
    public RespBean modifyRole(Role role) {
        if (rightsService.updateRole(role)) {
            return RespBean.ok();
        } else {
            return RespBean.error();
        }
    }

    @PutMapping("/reRights/{roleId}")
    public RespBean changeRights(@PathVariable Integer roleId, Integer[] ids) {
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
