package org.boluo.hr.service;

import org.boluo.hr.mapper.SalaryMapper;
import org.boluo.hr.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 🍍
 * @date 2023/10/1
 */
@Service
public class SobCfgService {

    private final SalaryMapper salaryMapper;

    @Autowired
    public SobCfgService(SalaryMapper salaryMapper) {
        this.salaryMapper = salaryMapper;
    }

    public List<Employee> selectEmpWithSalary() {
        return salaryMapper.selectEmployeeAndSalary();
    }

    public int countEmpWithSal() {
        return salaryMapper.countSalaryWithEmployee();
    }

    /**
     * 存储过程
     */
    public void insertSalAndEmp(Integer eId, Integer salId) {
        salaryMapper.insertEmployeeWithSalary(eId, salId);
    }
}
