package org.boluo.hr.mapper;

import org.boluo.hr.pojo.AdjustSalary;

import java.util.List;

/**
 * 调薪 数据层
 *
 * @author boluo
 */
public interface AdjustSalaryMapper {

    /**
     * 通过调薪id 删除调薪
     *
     * @param id 调薪id
     * @return 结果
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增调薪
     *
     * @param adjustSalary 调薪信息
     * @return 结果
     */
    int insertAdjustSalary(AdjustSalary adjustSalary);

    /**
     * 通过调薪id 查询调薪
     *
     * @param id 调薪id
     * @return 调薪信息
     */
    AdjustSalary selectByPrimaryKey(Integer id);

    /**
     * 通过调薪id 修改调薪
     *
     * @param adjustSalary 调薪信息
     * @return 结果
     */
    int updateByPrimaryKey(AdjustSalary adjustSalary);

    /**
     * 查询所有调薪
     *
     * @return 调薪集合
     */
    List<AdjustSalary> selectAll();
}