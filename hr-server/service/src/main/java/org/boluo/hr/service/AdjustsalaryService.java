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

    public boolean update(Adjustsalary adjustsalary) {
        return adjustsalaryMapper.updateByPrimaryKeySelective(adjustsalary) == 1;
    }

    public boolean delete(Integer id) {
        return adjustsalaryMapper.deleteByPrimaryKey(id) == 1;
    }

    public boolean insert(Adjustsalary adjustsalary) {
        return adjustsalaryMapper.insert(adjustsalary) == 1;
    }
}
