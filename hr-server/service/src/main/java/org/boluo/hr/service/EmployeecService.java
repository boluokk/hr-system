package org.boluo.hr.service;

import org.boluo.hr.mapper.EmployeeecMapper;
import org.boluo.hr.pojo.Employeeec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author @1352955539(boluo)
 * @date 2021/2/28 - 21:29
 */
@Service
public class EmployeesService {

    private final EmployeeecMapper employeeecMapper;

    @Autowired
    public EmployeesService(EmployeeecMapper employeeecMapper) {
        this.employeeecMapper = employeeecMapper;
    }

    public List<Employeeec> selectAll() {
        return employeeecMapper.selectAll();
    }

    public boolean updateOne(Employeeec employeeec) {
        return employeeecMapper.updateByPrimaryKeySelective(employeeec) == 1;
    }

    public boolean deleteOne(Integer id) {
        return employeeecMapper.deleteByPrimaryKey(id) == 1;
    }

    public boolean insertOne(Employeeec employeeec) {
        if (employeeec.getEcdate() == null) {
            employeeec.setEcdate(new Date());
        }
        Integer i = employeeecMapper.selectEid(employeeec.getWorkId());
        if (i != null) {
            employeeec.setEid(i);
            return employeeecMapper.insertSelective(employeeec) == 1;
        }
        return false;
    }
}
