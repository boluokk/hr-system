package org.boluo.hr.service;

import org.boluo.hr.mapper.SalaryMapper;
import org.boluo.hr.pojo.Salary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 🍍
 * @date 2023/10/1
 */
@Service
public class SalaryService {

    private final SalaryMapper salaryMapper;

    @Autowired
    public SalaryService(SalaryMapper salaryMapper) {
        this.salaryMapper = salaryMapper;
    }

    public List<Salary> selectAllSalary() {
        return salaryMapper.selectAll();
    }

    public boolean delete(Integer id) {
        return salaryMapper.deleteByPrimaryKey(id) == 1;
    }

    public boolean insert(Salary salary) {
        return salaryMapper.insertSelective(salary) == 1;
    }

    public boolean update(Salary salary) {
        return salaryMapper.updateByPrimaryKey(salary) == 1;
    }

    public boolean deleteMany(Integer[] ids) {
        return salaryMapper.deleteMany(ids) == ids.length;
    }
}
