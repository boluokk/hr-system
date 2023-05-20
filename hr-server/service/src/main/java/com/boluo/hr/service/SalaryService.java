package com.boluo.hr.service;

import com.boluo.hr.mapper.SalaryMapper;
import com.boluo.hr.pojo.Salary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author @1352955539(boluo)
 * @date 2021/2/21 - 11:21
 */
@Service
public class SalaryService {

    @Autowired
    SalaryMapper salaryMapper;

    public List<Salary> getAllSalary() {
        return salaryMapper.selectAll();
    }

    public int delOfOne(Integer id) {
        return salaryMapper.deleteByPrimaryKey(id);
    }

    public int addOfOne(Salary salary) {
        return salaryMapper.insertSelective(salary);
    }

    public int editSalary(Salary salary) {
        return salaryMapper.updateByPrimaryKey(salary);
    }

    public int deleteOfMany(Integer[] ids) {
        return salaryMapper.deleteOfMany(ids);
    }
}
