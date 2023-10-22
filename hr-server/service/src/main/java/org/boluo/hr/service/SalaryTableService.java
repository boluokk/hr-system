package org.boluo.hr.service;

import org.boluo.hr.mapper.SalaryTableMapper;
import org.boluo.hr.pojo.SalaryTableSearch;
import org.boluo.hr.pojo.SalaryTableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 工资表 业务层
 *
 * @author 🍍
 * @date 2023/10/22
 */

@Service
public class SalaryTableService {

    private final SalaryTableMapper salaryTableMapper;

    @Autowired
    public SalaryTableService(SalaryTableMapper salaryTableMapper) {
        this.salaryTableMapper = salaryTableMapper;
    }

    /**
     * 返回所有工资表
     *
     * @return 工资表集合
     */
    public List<SalaryTableView> selectAll(SalaryTableSearch salaryTableSearch) {
        return salaryTableMapper.selectAll(salaryTableSearch);
    }
}
