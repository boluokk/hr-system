package org.boluo.hr.service;

import org.boluo.hr.mapper.SalarymonthMapper;
import org.boluo.hr.pojo.SalaryMonth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 🍍
 * @date 2023/10/1
 */
@Service
public class SalarymontService {

    private final SalarymonthMapper salarymonthMapper;

    @Autowired
    public SalarymontService(SalarymonthMapper salarymonthMapper) {
        this.salarymonthMapper = salarymonthMapper;
    }

    public List<SalaryMonth> selectAll() {
        return salarymonthMapper.selectAll();
    }
}
