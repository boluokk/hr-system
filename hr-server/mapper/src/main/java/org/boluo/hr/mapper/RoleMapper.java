package com.boluo.hr.mapper;

import org.boluo.hr.pojo.RightsBean;
import org.boluo.hr.pojo.Role;
import org.boluo.hr.pojo.RoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper {
    int countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> getAllRole();

    List<Integer> getMenuIdWithRoleID(@Param("id") Integer id);

    int insertRightsByRoleId(@Param("bean") RightsBean rightsBean);

    int deleteRightsByRoleId(@Param("id") Integer roleid);

    int selectRightsCountByRoleId(@Param("id") Integer id);

    List<Role> selectByHrId(@Param("hid") Integer hid);

    int deleteByHrId(@Param("hrid") Integer hrid);

    int insertRolesByRoleIds(@Param("hid") Integer hid,@Param("roleIds") Integer[] roleIds);
}