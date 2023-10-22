package org.boluo.hr.service;

import org.boluo.hr.mapper.EmployeeSalaryMergeMapper;
import org.boluo.hr.mapper.SalaryMapper;
import org.boluo.hr.pojo.Employee;
import org.boluo.hr.pojo.EmployeeSalaryMerge;
import org.boluo.hr.pojo.SalaryConfigView;
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
    private final EmployeeSalaryMergeMapper employeeSalaryMergeMapper;

    @Autowired
    public SobCfgService(SalaryMapper salaryMapper,
                         EmployeeSalaryMergeMapper employeeSalaryMergeMapper) {
        this.salaryMapper = salaryMapper;
        this.employeeSalaryMergeMapper = employeeSalaryMergeMapper;
    }

    /**
     * 所有员工工资配置
     *
     * @return 员工集合
     */
    public List<SalaryConfigView> selectEmpWithSalary() {
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
     * 更换员工账套
     *
     * @param employeeSalaryMerge 员工账套关系信息
     * @return 结果
     */
    public boolean updateEmployeeSalary(EmployeeSalaryMerge employeeSalaryMerge) {
        return employeeSalaryMergeMapper.updateEmployeeSalaryMerge(employeeSalaryMerge) == 1;
    }

    public EmployeeSalaryMerge selectEmployeeSalaryMergeByEmployeeId(Integer employeeId) {
        return employeeSalaryMergeMapper.selectByEmployeeId(employeeId);
    }

    public boolean insertEmployeeSalaryMerge(EmployeeSalaryMerge employeeSalaryMerge) {
        return employeeSalaryMergeMapper.insertEmployeeSalaryMerge(employeeSalaryMerge) == 1;
    }

    public SalaryConfigView selectEmployeeSalaryByWorkId(String workId) {
        return salaryMapper.selectEmployeeSalaryByWorkId(workId);
    }
}
