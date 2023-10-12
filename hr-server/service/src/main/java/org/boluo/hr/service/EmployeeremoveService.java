package org.boluo.hr.service;

import org.boluo.hr.mapper.EmployeeremoveMapper;
import org.boluo.hr.pojo.Employeeremove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 🍍
 * @date 2023/10/1
 */
@Service
public class EmployeeremoveService {
    private final EmployeeremoveMapper employeeremoveMapper;

    @Autowired
    public EmployeeremoveService(EmployeeremoveMapper employeeremoveMapper) {
        this.employeeremoveMapper = employeeremoveMapper;
    }

    public List<Employeeremove> selectAll() {
        return employeeremoveMapper.selectAll();
    }

    public boolean delete(Integer id) {
        return employeeremoveMapper.deleteByPrimaryKey(id) == 1;
    }

    public boolean update(Employeeremove employeeremove) {
        return employeeremoveMapper.updateByPrimaryKey(employeeremove) == 1;
    }

    public boolean insert(Employeeremove employeeremove) {
        return employeeremoveMapper.insert(employeeremove) == 1;
    }
}
