package com.boluo.hr.service;

import com.boluo.hr.mapper.MenuMapper;
import com.boluo.hr.mapper.RoleMapper;
import com.boluo.hr.pojo.Menu;
import com.boluo.hr.pojo.RightsBean;
import com.boluo.hr.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author @1352955539(boluo)
 * @date 2021/2/6 - 21:28
 */
@Service
public class RightsService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MenuMapper menuMapper;


    public List<Role> getAllRole() {
        return roleMapper.getAllRole();
    }

    public List<Integer> getRightsWithRoleId(Integer id) {
        return roleMapper.getMenuIdWithRoleID(id);
    }

    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }

    public int  insertRole(Role role) {
        return roleMapper.insertSelective(role);
    }

    public int deleteWithRoleId(Integer id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    public int updateRoleName(Role role) {
        return roleMapper.updateByPrimaryKeySelective(role);
    }

//  更新
    public int deleteRights(Integer roleId) {
        return roleMapper.deleteRightsByRoleId(roleId);
    }
    public int insertRights(RightsBean rightsBean) {
        return roleMapper.insertRightsByRoleId(rightsBean);
    }

    public int selectRightsCountByRoleId(Integer id) {
        return roleMapper.selectRightsCountByRoleId(id);
    }

}
