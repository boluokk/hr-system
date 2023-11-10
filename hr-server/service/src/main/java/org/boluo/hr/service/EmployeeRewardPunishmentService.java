package org.boluo.hr.service;

import org.boluo.hr.mapper.EmployeeMapper;
import org.boluo.hr.mapper.EmployeeRewardPunishmentMapper;
import org.boluo.hr.pojo.EmployeeRewardPunishment;
import org.boluo.hr.pojo.InsertEmployeeRewardPunishment;
import org.boluo.hr.pojo.UploadEmployeeRewardPunishment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 奖惩 业务层
 *
 * @author 🍍
 * @date 2023/10/1
 */
@Service
public class EmployeeRewardPunishmentService {

    private final EmployeeRewardPunishmentMapper employeeRewardPunishmentMapper;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeRewardPunishmentService(EmployeeRewardPunishmentMapper employeeRewardPunishmentMapper,
                                           EmployeeMapper employeeMapper) {
        this.employeeRewardPunishmentMapper = employeeRewardPunishmentMapper;
        this.employeeMapper = employeeMapper;
    }

    /**
     * 所有奖惩集合
     *
     * @return 奖惩集合
     */
    public List<EmployeeRewardPunishment> selectAll() {
        return employeeRewardPunishmentMapper.selectAll();
    }

    /**
     * 修改奖惩
     *
     * @param uploadEmployeeRewardPunishment 奖惩信息
     * @return 结果
     */
    public boolean update(UploadEmployeeRewardPunishment uploadEmployeeRewardPunishment) {
        return employeeRewardPunishmentMapper.updateByPrimaryKey(uploadEmployeeRewardPunishment) == 1;
    }


    /**
     * 删除奖惩
     *
     * @param id 奖惩id
     * @return 结果
     */
    public boolean delete(Integer id) {
        return employeeRewardPunishmentMapper.deleteByPrimaryKey(id) == 1;
    }


    /**
     * 新增奖惩
     *
     * @param insertEmployeeRewardPunishment 奖惩信息
     * @return 结果
     */
    public boolean insert(InsertEmployeeRewardPunishment insertEmployeeRewardPunishment) {
        // todo 需要删除
        if (insertEmployeeRewardPunishment.getCreateDate() == null) {
            insertEmployeeRewardPunishment.setCreateDate(new Date());
        }
        return employeeRewardPunishmentMapper.insertEmployeeRewardPunishment(insertEmployeeRewardPunishment) == 1;
    }

}
