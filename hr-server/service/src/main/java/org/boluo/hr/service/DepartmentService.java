package org.boluo.hr.service;

import org.boluo.hr.mapper.DepartmentMapper;
import org.boluo.hr.pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门 业务层
 *
 * @author 🍍
 * @date 2023/10/1
 */
@Service
public class DepartmentService {

    private final DepartmentMapper departmentMapper;

    @Autowired
    public DepartmentService(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    /**
     * 通过父部门id 返回所有部门
     *
     * @param parentId 部门id
     * @return 部门集合
     */
    public List<Department> selectAllDepart(Integer parentId) {
        return departmentMapper.selectByParentId(parentId);
    }


    /**
     * 新增部门
     *
     * @param department 部门信息
     * @return 结果
     */
    public boolean insert(Department department) {
        return departmentMapper.insertDepartment(department) == 1;
    }


    /**
     * 更新部门
     *
     * @param department
     * @return
     */
    public boolean update(Department department) {
        return departmentMapper.updateByPrimaryKey(department) == 1;
    }

    /**
     * 最后一次插入部门id
     *
     * @return 部门id
     */
    public int lastInsertId() {
        return departmentMapper.lastInsertId();
    }


    /**
     * 删除部门
     *
     * @param id 部门id
     * @return
     */
    public int delete(Integer id) {
        return departmentMapper.deleteByPrimaryKey(id);
    }

    /**
     * 没有子部门
     *
     * @param id 父部门id
     * @return 结果
     */
    public boolean noChildren(Integer id) {
        return departmentMapper.selectCountByParentId(id) != 0;
    }

    /**
     * 所有开启部门
     *
     * @return 部门集合
     */
    public List<Department> selectAllDepWithDisabled() {
        return departmentMapper.selectAllDepWithDisabled();
    }

    /**
     * 通过部门路径 删除部门
     *
     * @param depPath 部门路径
     * @return 结果
     */
    public boolean deleteByDepPath(String depPath) {
        return departmentMapper.deleteByDepPath(depPath) >= 1;
    }

    /**
     * 通过部门名称 查询部门
     *
     * @param name 部门名称
     * @return 部门集合
     */
    public List<Department> selectByName(String name) {
        return departmentMapper.selectByName(name);
    }

    /**
     * 返回所有部门
     *
     * @return 部门集合
     */
    public List<Department> selectAll() {
        return departmentMapper.selectAll();
    }

}
