package org.boluo.hr.mapper;

import org.apache.ibatis.annotations.Param;
import org.boluo.hr.pojo.Menu;

import java.util.List;
import java.util.Set;


/**
 * 菜单 数据层
 *
 * @author boluo
 */
public interface MenuMapper {

    /**
     * 删除菜单
     *
     * @param id 菜单id
     * @return 结果
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增菜单
     *
     * @param menu 菜单信息
     * @return 结果
     */
    int insertMenu(Menu menu);

    /**
     * 通过主键 查询菜单
     *
     * @param id 菜单id
     * @return 菜单信息
     */
    Menu selectByPrimaryKey(Integer id);

    /**
     * 修改菜单
     *
     * @param menu 菜单信息
     * @return 结果
     */
    int updateByPrimaryKey(Menu menu);

    /**
     * 查询所有带角色的菜单
     *
     * @return 带角色菜单集合
     */
    List<Menu> selectAllMenusWithRole();

    /**
     * 通过人事id 查询所拥有的菜单
     *
     * @param id 人事id
     * @return 菜单集合
     */
    List<Menu> selectMenusByHrId(@Param("id") Integer id);

    /**
     * 查询所有菜单
     *
     * @return 菜单集合
     */
    List<Menu> selectAllMenus();

    /**
     * 查询所有菜单id
     *
     * @return 菜单id集合
     */
    Set<Integer> selectAllMenuIds();
}