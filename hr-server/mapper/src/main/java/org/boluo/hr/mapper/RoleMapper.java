package org.boluo.hr.mapper;

import org.apache.ibatis.annotations.Param;
import org.boluo.hr.pojo.InsertRole;
import org.boluo.hr.pojo.RightsBean;
import org.boluo.hr.pojo.Role;
import org.boluo.hr.pojo.UploadRole;

import java.util.List;

/**
 * 角色 数据层
 *
 * @author boluo
 */
public interface RoleMapper {

    Role selectByName(@Param("name") String name);

    /**
     * 删除角色
     *
     * @param id 角色id
     * @return 结果
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增角色
     *
     * @param insertRole 角色信息
     * @return 结果
     */
    int insertRole(InsertRole insertRole);

    /**
     * 查询角色
     *
     * @param id 角色id
     * @return 角色信息
     */
    Role selectByPrimaryKey(Integer id);

    /**
     * 修改角色
     *
     * @param uploadRole 角色信息
     * @return 结果
     */
    int updateByPrimaryKey(UploadRole uploadRole);

    /**
     * 查询所有角色
     *
     * @return 角色集合
     */
    List<Role> selectAllRole();

    /**
     * 查询角色权限id（菜单id）
     *
     * @param id 角色id
     * @return 权限id集合
     */
    List<Integer> selectRightsByRoleId(@Param("id") Integer id);

    /**
     * 对角色新增权限
     *
     * @param rightsBean 权限信息
     * @return 结果
     */
    int insertRightsByRoleId(@Param("bean") RightsBean rightsBean);

    /**
     * 删除角色权限
     *
     * @param roleId 角色id
     * @return 结果
     */
    int deleteRightsByRoleId(@Param("id") Integer roleId);

    /**
     * 查询角色权限个数
     *
     * @param id 角色id
     * @return 权限个数
     */
    int selectRightsCountByRoleId(@Param("id") Integer id);

    /**
     * 查询角色(人事)所有权限
     *
     * @param hid 人事id
     * @return 角色集合
     */
    List<Role> selectByHrId(@Param("hid") Integer hid);

    /**
     * 查询人事角色个数
     *
     * @param hrId 人事id
     * @return 角色个数
     */
    int selectRoleCountByHrId(@Param("hrId") Integer hrId);

    /**
     * 删除人事角色
     *
     * @param hrId 人事id
     * @return 结果
     */
    int deleteByHrId(@Param("hrId") Integer hrId);

    /**
     * 对人事 新增角色
     *
     * @param hid     人事id
     * @param roleIds 角色id数组
     * @return 结果
     */
    int insertRolesByRoleIds(@Param("hid") Integer hid, @Param("roleIds") Integer[] roleIds);
}