package com.boluo.hr.service;

import org.boluo.hr.mapper.AdjustsalaryMapper;
import org.boluo.hr.pojo.Adjustsalary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author @1352955539(boluo)
 * @date 2021/2/28 - 21:43
 */
@Service
public class AdjustsalaryService {

    @Autowired
    AdjustsalaryMapper adjustsalaryMapper;

    /**
     * @Description: 获取所有职位
     * @Param: []
     * @Return: java.util.List<org.boluo.hr.pojo.Adjustsalary>
     */
    public List<Adjustsalary> getAll() {
        return adjustsalaryMapper.selectAll();
    }
}
