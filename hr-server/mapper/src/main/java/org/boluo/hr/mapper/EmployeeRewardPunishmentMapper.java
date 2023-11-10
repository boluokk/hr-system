package org.boluo.hr.mapper;

import org.apache.ibatis.annotations.Param;
import org.boluo.hr.pojo.EmployeeRewardPunishment;
import org.boluo.hr.pojo.InsertEmployeeRewardPunishment;
import org.boluo.hr.pojo.UploadEmployeeRewardPunishment;

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
     * @param insertEmployeeRewardPunishment 奖惩信息
     * @return 结果
     */
    int insertEmployeeRewardPunishment(InsertEmployeeRewardPunishment insertEmployeeRewardPunishment);

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
     * @param uploadEmployeeRewardPunishment 奖惩信息
     * @return 结果
     */
    int updateByPrimaryKey(UploadEmployeeRewardPunishment uploadEmployeeRewardPunishment);

    /**
     * 所有奖惩信息
     *
     * @return 奖惩集合
     */
    List<EmployeeRewardPunishment> selectAll();

}