package org.boluo.hr.service;

import org.bluo.common.redis.util.RedisCache;
import org.bluo.common.redis.util.RedisConstants;
import org.boluo.hr.exception.BusinessException;
import org.boluo.hr.mapper.MenuMapper;
import org.boluo.hr.mapper.RoleMapper;
import org.boluo.hr.pojo.*;
import org.boluo.hr.util.CheckUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 权限 业务逻辑
 *
 * @author 🍍
 * @date 2023/10/1
 */
@Service
public class RightsService {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private MenuMapper menuMapper;
    @Resource
    private MenuService menuService;
    @Resource
    private RedisCache redisCache;

    public Role selectRoleByName(String roleName) {
        return roleMapper.selectByName(roleName);
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
     * @param insertRole 角色信息
     * @return 结果
     */
    public boolean insertRole(InsertRole insertRole) {
        return roleMapper.insertRole(insertRole) == 1;
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
     * @param uploadRole 角色信息
     * @return 结果
     */
    public boolean updateRole(UploadRole uploadRole) {
        return roleMapper.updateByPrimaryKey(uploadRole) == 1;
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


    /**
     * 修改角色权限
     *
     * @param uploadRoleMenu 角色权限信息
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean modifyRights(UploadRoleMenu uploadRoleMenu) {
        int countRight = selectRightsCountByRoleId(uploadRoleMenu.getRoleId());
        if (countRight > 0 && !deleteRights(uploadRoleMenu.getRoleId())) {
            throw new BusinessException("删除权限失败");
        }
        // 没有传入菜单id, 则不进行任何操作
        if (!CheckUtil.hasLength(uploadRoleMenu.getMenuIds())) {
            return true;
        }
        // 更新, 重新缓存下权限数据
        if (insertRights(new RightsBean(uploadRoleMenu.getMenuIds(),
                uploadRoleMenu.getRoleId()))) {
            redisCache.set(RedisConstants.All_MENU,
                    new AllMenu().setAllMenu(menuService.selectAllMenu()), 5L,
                    TimeUnit.MINUTES);
            return true;
        }
        throw new BusinessException("新增权限失败");
    }

    /**
     * 查询所有菜单id
     *
     * @return 菜单id集合
     */
    public Set<Integer> selectAllMenuIds() {
        return menuMapper.selectAllMenuIds();
    }
}
