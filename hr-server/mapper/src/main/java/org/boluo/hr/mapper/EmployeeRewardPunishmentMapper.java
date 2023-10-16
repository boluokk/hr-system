package org.boluo.hr.mapper;

import org.apache.ibatis.annotations.Param;
import org.boluo.hr.pojo.EmployeeRewardPunishment;

import java.util.List;

/**
 * 员工奖惩 数据层
 *
 * @author boluo
 */
public interface EmployeeRewardPunishmentMapper {

    /**
     * 删除奖惩
     *
     * @param id 奖惩id
     * @return 结果
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增奖惩
     *
     * @param employeeRewardPunishment 奖惩信息
     * @return 结果
     */
    int insertEmployeeRewardPunishment(EmployeeRewardPunishment employeeRewardPunishment);

    /**
     * 通过id 查询奖惩信息
     *
     * @param id 奖惩id
     * @return 奖惩信息
     */
    EmployeeRewardPunishment selectByPrimaryKey(Integer id);

    /**
     * 修改奖惩
     *
     * @param employeeRewardPunishment 奖惩信息
     * @return 结果
     */
    int updateByPrimaryKey(EmployeeRewardPunishment employeeRewardPunishment);

    /**
     * 所有奖惩信息
     *
     * @return 奖惩集合
     */
    List<EmployeeRewardPunishment> selectAll();

}