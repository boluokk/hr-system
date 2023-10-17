package org.boluo.hr.service;

import org.boluo.hr.mapper.AdjustSalaryMapper;
import org.boluo.hr.pojo.AdjustSalary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 调薪 业务层
 *
 * @author 🍍
 * @date 2023/10/1
 */
@Service
public class AdjustSalaryService {

    private final AdjustSalaryMapper adjustSalaryMapper;

    @Autowired
    public AdjustSalaryService(AdjustSalaryMapper adjustSalaryMapper) {
        this.adjustSalaryMapper = adjustSalaryMapper;
    }

    /**
     * 返回所有调薪
     *
     * @return 调薪集合
     */
    public List<AdjustSalary> selectAll() {
        return adjustSalaryMapper.selectAll();
    }

    /**
     * 更新调薪
     *
     * @param adjustSalary 调薪信息
     * @return 结果
     */
    public boolean update(AdjustSalary adjustSalary) {
        return adjustSalaryMapper.updateByPrimaryKey(adjustSalary) == 1;
    }

    /**
     * 删除调薪
     *
     * @param id 调薪id
     * @return 结果
     */
    public boolean delete(Integer id) {
        return adjustSalaryMapper.deleteByPrimaryKey(id) == 1;
    }

    /**
     * 新增调薪
     *
     * @param adjustSalary 调薪信息
     * @return 结果
     */
    public boolean insert(AdjustSalary adjustSalary) {
        return adjustSalaryMapper.insertAdjustSalary(adjustSalary) == 1;
    }
}
