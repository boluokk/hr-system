package com.boluo.hr.service;

import org.boluo.hr.mapper.SalarymonthMapper;
import org.boluo.hr.pojo.SalaryMonth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author @1352955539(boluo)
 * @date 2021/3/25 - 22:30
 */
@Service
public class SalarymontService {

    @Autowired
    private SalarymonthMapper salarymonthMapper;

    public List<SalaryMonth> SearchAll() {
        return salarymonthMapper.selectAll();
    }
}
