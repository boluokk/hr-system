package org.boluo.hr.pojo;

import lombok.Data;

/**
 * 员工奖惩页头统计 实体
 *
 * @author 🍍
 * @date 2023/10/31
 */
@Data
public class EmployeeScoreHeaderStatistics {
    /**
     * 奖惩总人数
     */
    private Integer allCount;
    /**
     * 奖励人数
     */
    private Integer rewardCount;
    /**
     * 处罚人数
     */
    private Integer punishmentCount;
}
