package org.boluo.hr.mapper;

import org.apache.ibatis.annotations.Param;
import org.boluo.hr.pojo.Menu;

import java.util.List;

public interface MenuMapper {
    int countByExample(MenuExample example);

    int deleteByExample(MenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    List<Menu> selectByExample(MenuExample example);

    Menu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Menu record, @Param("example") MenuExample example);

    int updateByExample(@Param("record") Menu record, @Param("example") MenuExample example);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> getAllMenusWithRole();

    List<Menu> getMenusByHrId(@Param("id") Integer hrid);

    List<Menu> selectAllMenus();
}