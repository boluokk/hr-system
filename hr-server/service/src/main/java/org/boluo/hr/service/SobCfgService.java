package org.boluo.hr.service;

import org.boluo.hr.mapper.SalaryMapper;
import org.boluo.hr.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 工资配置 业务层
 *
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

    /**
     * 所有员工工资配置
     *
     * @return 员工集合
     */
    public List<Employee> selectEmpWithSalary() {
        return salaryMapper.selectEmployeeAndSalary();
    }

    /**
     * 有工资信息员工数
     *
     * @return 带工资员工数
     */
    public int countEmpWithSal() {
        return salaryMapper.countSalaryWithEmployee();
    }

    /**
     * 新增员工工资
     *
     * @param eId 员工id
     * @param salId 工资账套id
     */
    public void insertSalAndEmp(Integer eId, Integer salId) {
        salaryMapper.insertEmployeeWithSalary(eId, salId);
    }
}
