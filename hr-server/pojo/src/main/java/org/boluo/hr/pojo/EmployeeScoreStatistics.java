package org.boluo.hr.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 员工积分统计 实体
 *
 * @author 🍍
 * @date 2023/10/31
 */
@Data
public class EmployeeScoreStatistics {
    /**
     * 计数
     */
    private Integer count;
    /**
     * 时间
     */
    private Date time;
    /**
     * 奖励或者处罚
     */
    private String rewardOrPunishment;
}
