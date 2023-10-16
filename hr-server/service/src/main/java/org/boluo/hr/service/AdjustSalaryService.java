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

    private final AdjustSalaryMapper adjustsalaryMapper;

    @Autowired
    public AdjustSalaryService(AdjustSalaryMapper adjustsalaryMapper) {
        this.adjustsalaryMapper = adjustsalaryMapper;
    }

    /**
     * 返回所有调薪
     *
     * @return 调薪集合
     */
    public List<AdjustSalary> selectAll() {
        return adjustsalaryMapper.selectAll();
    }

    /**
     * 更新调薪
     *
     * @param adjustsalary 调薪信息
     * @return 结果
     */
    public boolean update(AdjustSalary adjustsalary) {
        return adjustsalaryMapper.updateByPrimaryKey(adjustsalary) == 1;
    }

    /**
     * 删除调薪
     *
     * @param id 调薪id
     * @return 结果
     */
    public boolean delete(Integer id) {
        return adjustsalaryMapper.deleteByPrimaryKey(id) == 1;
    }

    /**
     * 新增调薪
     *
     * @param adjustsalary 调薪信息
     * @return 结果
     */
    public boolean insert(AdjustSalary adjustsalary) {
        return adjustsalaryMapper.insertAdjustSalary(adjustsalary) == 1;
    }
}
