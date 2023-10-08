package org.boluo.hr.service;

import org.boluo.hr.mapper.EmployeeecMapper;
import org.boluo.hr.pojo.Employeeec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 🍍
 * @date 2023/10/1
 */
@Service
public class EmployeecService {

    private final EmployeeecMapper employeeecMapper;

    @Autowired
    public EmployeecService(EmployeeecMapper employeeecMapper) {
        this.employeeecMapper = employeeecMapper;
    }

    public List<Employeeec> selectAll() {
        return employeeecMapper.selectAll();
    }

    public boolean update(Employeeec employeeec) {
        return employeeecMapper.updateByPrimaryKeySelective(employeeec) == 1;
    }

    public boolean delete(Integer id) {
        return employeeecMapper.deleteByPrimaryKey(id) == 1;
    }

    public boolean insert(Employeeec employeeec) {
        if (employeeec.getEcdate() == null) {
            employeeec.setEcdate(new Date());
        }
        return employeeecMapper.insertSelective(employeeec) == 1;
    }

    public Integer selectByWorkId(String workId) {
        return employeeecMapper.selectByWorkId(workId);
    }
}
