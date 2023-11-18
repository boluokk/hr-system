package org.boluo.hr.service;

import org.boluo.hr.mapper.SalaryMapper;
import org.boluo.hr.pojo.Salary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 🍍
 * @date 2023/10/09
 */

@Service
public class EmployeeSalaryService {

    @Resource
    private SalaryMapper salaryMapper;

    public List<Salary> selectAll() {
        return salaryMapper.selectAll();
    }
}
