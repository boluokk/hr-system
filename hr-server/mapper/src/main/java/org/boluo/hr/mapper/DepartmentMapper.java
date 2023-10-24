package org.boluo.hr.mapper;

import org.apache.ibatis.annotations.Param;
import org.boluo.hr.pojo.Department;

import java.util.List;

/**
 * 部门 数据层
 *
 * @author boluo
 */
public interface DepartmentMapper {

    /**
     * 通过部门id 删除部门
     *
     * @param id 部门id
     * @return 结果
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增部门
     *
     * @param department 部门信息
     * @return 结果
     */
    int insertDepartment(Department department);

    /**
     * 通过部门id 查询部门
     *
     * @param id 部门id
     * @return 部门信息
     */
    Department selectByPrimaryKey(Integer id);

    /**
     * 修改部门
     *
     * @param department 部门信息
     * @return 结果
     */
    int updateByPrimaryKey(Department department);

    /**
     * 通过部门父id 查询所有子部门
     *
     * @param id 父部门id
     * @return 子部门集合
     */
    List<Department> selectByParentId(@Param("id") Integer id);

    /**
     * 查询部门最后插入id
     *
     * @return 部门id
     */
    int lastInsertId();

    /**
     * 根据父部门id 查询所有部门数
     *
     * @param id 父部门id
     * @return 部门个数
     */
    int selectCountByParentId(@Param("id") Integer id);

    /**
     * 查询所有开启的部门
     *
     * @return 部门集合
     */
    List<Department> selectAllDepWithDisabled();

    /**
     * 通过部门路径 删除部门
     *
     * @param path 部门路径
     * @return 结果
     */
    int deleteByDepPath(@Param("depPath") String depPath);

    /**
     * 通过部门名称 查询部门
     *
     * @param name 部门名称
     * @return 部门集合
     */
    List<Department> selectByName(@Param("name") String name);

    /**
     * 查询所有部门
     *
     * @return 部门集合
     */
    List<Department> selectAll();
}