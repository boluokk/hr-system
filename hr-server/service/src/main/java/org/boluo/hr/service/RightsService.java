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
 * 权限 业务逻辑
 *
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

    /**
     * 返回所有角色
     *
     * @return 角色集合
     */
    public List<Role> selectAllRoles() {
        return roleMapper.selectAllRole();
    }

    /**
     * 通过角色id 查询权限
     *
     * @param roleId 角色id
     * @return 权限集合
     */
    public List<Integer> selectRightsByRoleId(Integer roleId) {
        return roleMapper.selectRightsByRoleId(roleId);
    }

    /**
     * 查询所有菜单
     *
     * @return 菜单集合
     */
    public List<Menu> selectAllMenus() {
        return menuMapper.selectAllMenus();
    }

    /**
     * 新增角色
     *
     * @param role 角色信息
     * @return 结果
     */
    public boolean insertRole(Role role) {
        return roleMapper.insertRole(role) == 1;
    }

    /**
     * 删除角色
     *
     * @param id 角色id
     * @return 结果
     */
    public boolean deleteRoleById(Integer id) {
        return roleMapper.deleteByPrimaryKey(id) == 1;
    }

    /**
     * 修改角色
     *
     * @param role 角色信息
     * @return 结果
     */
    public boolean updateRole(Role role) {
        return roleMapper.updateByPrimaryKey(role) == 1;
    }

    /**
     * 删除角色权限
     *
     * @param roleId 角色id
     * @return 结果
     */
    public boolean deleteRights(Integer roleId) {
        return roleMapper.deleteRightsByRoleId(roleId) > 0;
    }

    /**
     * 新增权限
     *
     * @param rightsBean 权限信息
     * @return 结果
     */
    public boolean insertRights(RightsBean rightsBean) {
        return roleMapper.insertRightsByRoleId(rightsBean) > 0;
    }

    /**
     * 查询角色权限个数
     *
     * @param roleId 角色id
     * @return 权限个数
     */
    public int selectRightsCountByRoleId(Integer roleId) {
        return roleMapper.selectRightsCountByRoleId(roleId);
    }

}
