package org.boluo.hr.service;

import org.boluo.hr.mapper.MenuMapper;
import org.boluo.hr.mapper.RoleMapper;
import org.boluo.hr.pojo.Menu;
import org.boluo.hr.pojo.RightsBean;
import org.boluo.hr.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 🍍
 * @date 2023/10/1
 */
@Service
public class RightsService {

    private final RoleMapper roleMapper;
    private final MenuMapper menuMapper;

    @Autowired
    public RightsService(RoleMapper roleMapper, MenuMapper menuMapper) {
        this.roleMapper = roleMapper;
        this.menuMapper = menuMapper;
    }

    public List<Role> selectAllRoles() {
        return roleMapper.selectAllRole();
    }

    public List<Integer> selectRightsByRoleId(Integer id) {
        return roleMapper.selectRightsByRoleId(id);
    }

    public List<Menu> selectAllMenus() {
        return menuMapper.selectAllMenus();
    }

    public boolean insertRole(Role role) {
        return roleMapper.insertSelective(role) == 1;
    }

    public boolean deleteRoleById(Integer id) {
        return roleMapper.deleteByPrimaryKey(id) == 1;
    }

    public boolean updateRole(Role role) {
        return roleMapper.updateByPrimaryKeySelective(role) == 1;
    }

//  更新
    public boolean deleteRights(Integer roleId) {
        return roleMapper.deleteRightsByRoleId(roleId) > 0;
    }
    public boolean insertRights(RightsBean rightsBean) {
        return roleMapper.insertRightsByRoleId(rightsBean) > 0;
    }

    public int selectRightsCountByRoleId(Integer id) {
        return roleMapper.selectRightsCountByRoleId(id);
    }

}
