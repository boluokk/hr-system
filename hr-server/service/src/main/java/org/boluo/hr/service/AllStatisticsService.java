package org.boluo.hr.service;

import org.boluo.hr.mapper.AllStatisticsMapper;
import org.boluo.hr.pojo.AllStatisticsHeader;
import org.boluo.hr.pojo.DepartmentStatistics;
import org.boluo.hr.pojo.SalaryStatistics;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 综合统计信息 业务层
 *
 * @author 🍍
 * @date 2023/10/31
 */
@Service
public class AllStatisticsService {
    @Resource
    private AllStatisticsMapper allStatisticsMapper;

    /**
     * 综合统计页头信息
     *
     * @return 页头数据
     */
    public AllStatisticsHeader selectHeaderCount() {
        return allStatisticsMapper.selectHeaderCount();
    }

    /**
     * 工资统计数据
     *
     * @return 工资统计数据集合
     */
    public List<SalaryStatistics> selectSalaryStatistics() {
        return allStatisticsMapper.selectSalaryStatistics();
    }

    /**
     * 查询部门结构
     *
     * @return 部门结构
     */
    public DepartmentStatistics selectDepartmentStruct() {
        return allStatisticsMapper.selectByParentIdOnlyName(-1);
    }
}
