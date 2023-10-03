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

    public List<Employee> selectEmpWithSalary(Integer num, Integer size) {
        if (num != null && size != null) {
            int realNum = size * (num - 1);
            return salaryMapper.selectEmpAndSalary(realNum, size);
        }
        return null;
    }

    public int countEmpWithSal() {
        return salaryMapper.countSalaryWithEmp();
    }

    /**
     * 存储过程
     */
    public void insertSalAndEmp(Integer eid, Integer salid) {
        salaryMapper.insertEmpWithSal(eid, salid);
    }
}
