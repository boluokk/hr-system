package org.boluo.hr.service;

import org.boluo.hr.mapper.AdjustsalaryMapper;
import org.boluo.hr.pojo.Adjustsalary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 🍍
 * @date 2023/10/1
 */
@Service
public class AdjustsalaryService {

    private final AdjustsalaryMapper adjustsalaryMapper;

    @Autowired
    public AdjustsalaryService(AdjustsalaryMapper adjustsalaryMapper) {
        this.adjustsalaryMapper = adjustsalaryMapper;
    }

    public List<Adjustsalary> selectAll() {
        return adjustsalaryMapper.selectAll();
    }
}