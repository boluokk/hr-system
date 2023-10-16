package org.boluo.hr.service;

import org.boluo.hr.mapper.SalaryMonthMapper;
import org.boluo.hr.pojo.SalaryMonth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 工资月末 业务层
 *
 * @author 🍍
 * @date 2023/10/1
 */
@Service
public class SalaryMontService {

    private final SalaryMonthMapper salaryMonthMapper;

    @Autowired
    public SalaryMontService(SalaryMonthMapper salaryMonthMapper) {
        this.salaryMonthMapper = salaryMonthMapper;
    }

    /**
     * 返回所有工资月末信息
     *
     * @return 工资月末集合
     */
    public List<SalaryMonth> selectAll() {
        return salaryMonthMapper.selectAll();
    }

    /**
     * 通过员工号 返回员工的所有工资月末
     *
     * @param employeeWorkId 员工号
     * @return 工资月末集合
     */
    public List<SalaryMonth> selectByEmployeeWorkId(Integer employeeWorkId) {
        return salaryMonthMapper.selectByEmployeeWorkId(employeeWorkId);
    }
}
