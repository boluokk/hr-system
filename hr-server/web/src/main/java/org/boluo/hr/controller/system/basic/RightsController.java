package com.boluo.hr.controller.system.basic;

import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.pojo.RightsBean;
import org.boluo.hr.pojo.Role;
import com.boluo.hr.service.RightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author @1352955539(boluo)
 * @date 2021/2/6 - 21:37
 */
@RestController
@RequestMapping("/system/basic/rights")
public class RightsController {

    @Autowired
    private RightsService rightsService;

    @GetMapping("/")
    public RespBean showRole() {
        return RespBean.ok(rightsService.getAllRole());
    }

    @GetMapping("/{id}")
    public RespBean getRishtsWithRoleId(@PathVariable Integer id) {
        return RespBean.ok(rightsService.getRightsWithRoleId(id));
    }

    @GetMapping("/menus")
    public RespBean showMenu() {
        return RespBean.ok(rightsService.getAllMenus());
    }

    @PutMapping("/")
    public RespBean addOneRole(Role role) {
        String roleEnname = "ROLE_";
        role.setName(roleEnname + role.getName());
        int i = rightsService.insertRole(role);
        if (i == 1) {
            return RespBean.ok("添加成功！");
        } else {
            return RespBean.error("添加失败！");
        }
    }

    @DeleteMapping("/{id}")
    public RespBean deleteOneRole(@PathVariable Integer id) {
        int i = rightsService.deleteWithRoleId(id);
        if (i == 1) {
            return RespBean.ok("删除成功！");
        } else {
            return RespBean.error("删除失败！");
        }
    }

    @PutMapping("/changeRolename")
    public RespBean updateRoleName(Role role) {
        int i = rightsService.updateRoleName(role);
        if (i == 1) {
            return RespBean.ok("修改成功！");
        } else {
            return RespBean.error("修改失败！");
        }
    }

    @PutMapping("/ReRights/{roleId}")
    public RespBean changeRights(@PathVariable Integer roleId, Integer[] ids) {
        int coutRighst = rightsService.selectRightsCountByRoleId(roleId);
        if (coutRighst > 0) {
            int i = rightsService.deleteRights(roleId);
            if (i > 0) {
                if(ids == null) {
                    return RespBean.ok("修改成功！");
                }
                int i1 = rightsService.insertRights(new RightsBean(ids, roleId));
                if (i1 > 0) {
                    return RespBean.ok("修改成功！");
                } else {
                    return RespBean.error("修改失败！");
                }
            } else {
                return RespBean.error("修改失败！");
            }
        } else {
            if(ids == null) {
                return RespBean.ok("修改成功！");
            }
            int i1 = rightsService.insertRights(new RightsBean(ids, roleId));
            if (i1 > 0) {
                return RespBean.ok("修改成功！");
            } else {
                return RespBean.error("修改失败！");
            }
        }
    }
}
