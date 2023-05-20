package com.boluo.hr.service;

import com.boluo.hr.mapper.DepartmentMapper;
import com.boluo.hr.pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author @1352955539(boluo)
 * @date 2021/2/8 - 22:44
 */
@Service
public class DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    public List<Department> getAllDepart(Integer parentid) {
        return departmentMapper.selectByParentId(parentid);
    }

    public int insertDeparOfOne(Department department) {
        return departmentMapper.insertSelective(department);
    }

    public int updateDeparOfOne(Department department) {
        return departmentMapper.updateByPrimaryKeySelective(department);
    }

//    插入的id值
    public int last_insert_id() {
        return departmentMapper.lastInsertId();
    }

    public int deleteDepWithId(Integer id) {
        return departmentMapper.deleteByPrimaryKey(id);
    }

    public int selectCountbyParenId(Integer id) {
        return departmentMapper.selectCountParentId(id);
    }

    public List<Department> getAllDepWithEnabledIsFalse() {
        return departmentMapper.getAllDepWithEnabledIsFalse();
    }

    public int deleteBydepPath(String path) {
        return departmentMapper.deleteBydepPath(path);
    }

    public List<Department> getOneBysearch(String name) {
        return departmentMapper.selectbyName(name);
    }

    public List<Department> getAllDepInfo() {
        return departmentMapper.getalldepBy();
    }
}
