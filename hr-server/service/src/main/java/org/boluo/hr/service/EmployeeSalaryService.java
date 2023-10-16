package org.boluo.hr.service;

import org.boluo.hr.mapper.SalaryMapper;
import org.boluo.hr.pojo.Salary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 🍍
 * @date 2023/10/09
 */

@Service
public class EmployeeSalaryService {

    private final SalaryMapper salaryMapper;

    @Autowired
    public EmployeeSalaryService(SalaryMapper salaryMapper) {
        this.salaryMapper = salaryMapper;
    }

    public List<Salary> selectAll() {
        return salaryMapper.selectAll();
    }
}
