package org.boluo.hr.service;

import org.boluo.hr.mapper.DepartmentMapper;
import org.boluo.hr.pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
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

    public List<Department> selectAllDepart(Integer parentId) {
        return departmentMapper.selectByParentId(parentId);
    }

    public boolean insert(Department department) {
        return departmentMapper.insertSelective(department) == 1;
    }

    public boolean update(Department department) {
        return departmentMapper.updateByPrimaryKeySelective(department) == 1;
    }

    // 插入的id值
    public int lastInsertId() {
        return departmentMapper.lastInsertId();
    }

    public int delete(Integer id) {
        return departmentMapper.deleteByPrimaryKey(id);
    }

    public boolean selectCountByParenId(Integer id) {
        return departmentMapper.selectCountParentId(id) != 0;
    }

    public List<Department> selectAllDepWithDisabled() {
        return departmentMapper.selectAllDepWithDisabled();
    }

    public boolean deleteByDepPath(String path) {
        return departmentMapper.deleteByDepPath(path) == 1;
    }

    public List<Department> selectByName(String name) {
        return departmentMapper.selectByName(name);
    }

    public List<Department> selectAll() {
        return departmentMapper.selectAll();
    }
}
