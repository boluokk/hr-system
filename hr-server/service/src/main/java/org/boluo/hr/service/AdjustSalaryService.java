package org.boluo.hr.service;

import org.boluo.hr.mapper.AdjustSalaryMapper;
import org.boluo.hr.pojo.AdjustSalaryView;
import org.boluo.hr.pojo.InsertAdjustSalary;
import org.boluo.hr.pojo.UploadAdjustSalary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 调薪 业务层
 *
 * @author 🍍
 * @date 2023/10/1
 */
@Service
public class AdjustSalaryService {
    @Resource
    private AdjustSalaryMapper adjustSalaryMapper;

    /**
     * 返回所有调薪
     *
     * @return 调薪集合
     */
    public List<AdjustSalaryView> selectAll() {
        return adjustSalaryMapper.selectAll();
    }

    /**
     * 更新调薪
     *
     * @param uploadAdjustSalary 调薪信息
     * @return 结果
     */
    public boolean update(UploadAdjustSalary uploadAdjustSalary) {
        return adjustSalaryMapper.updateByPrimaryKey(uploadAdjustSalary) == 1;
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
     * @param insertAdjustSalary 调薪信息
     * @return 结果
     */
    public boolean insert(InsertAdjustSalary insertAdjustSalary) {
        return adjustSalaryMapper.insertAdjustSalary(insertAdjustSalary) == 1;
    }
}
