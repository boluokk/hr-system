package com.boluo.hr.service;

import org.boluo.hr.mapper.EmployeeMapper;
import org.boluo.hr.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author @1352955539(boluo)
 * @date 2021/3/28 - 13:32
 */
@Service
public class EmpInfoService {

    @Autowired
    private EmployeeMapper employeeMapper;

    public List<Employee> selectEmp(String empName) {
        return employeeMapper.selectByEmpName(empName);
    }

    public int updateEmp(Employee emp) {
        return employeeMapper.updateByPrimaryKey(emp);
    }

    public int deleteEmp(Integer id) {
        return employeeMapper.deleteByPrimaryKey(id);
    }


}
