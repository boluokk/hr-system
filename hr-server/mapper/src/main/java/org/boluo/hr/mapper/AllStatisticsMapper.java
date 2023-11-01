package org.boluo.hr.mapper;

import org.boluo.hr.pojo.AllStatisticsHeader;
import org.boluo.hr.pojo.DepartmentStatistics;
import org.boluo.hr.pojo.SalaryStatistics;

import java.util.List;

/**
 * 综合统计信息 数据层
 *
 * @author 🍍
 * @date 2023/10/31
 */
public interface AllStatisticsMapper {

    /**
     * 查询页头数据
     *
     * @return 页头数据
     */
    AllStatisticsHeader selectHeaderCount();

    /**
     * 查询工资统计
     *
     * @return 工资统计集合
     */
    List<SalaryStatistics> selectSalaryStatistics();

    /**
     * 查询部门结构统计
     *
     * @param id 部门id
     * @return 部门结构
     */
    DepartmentStatistics selectByParentIdOnlyName(Integer id);
}
