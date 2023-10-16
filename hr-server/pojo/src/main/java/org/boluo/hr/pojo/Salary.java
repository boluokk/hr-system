package org.boluo.hr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 工资账套 实体
 *
 * @author boluo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Salary {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 基本工资
     */
    private Integer basicSalary;
    /**
     * 奖金
     */
    private Integer bonus;
    /**
     * 午餐补助
     */
    private Integer lunchSalary;
    /**
     * 交通补助
     */
    private Integer trafficSalary;
    /**
     * 应发工资
     */
    private Integer allSalary;
    /**
     * 养老金基数
     */
    private Integer pensionBase;
    /**
     * 养老金比率
     */
    private Float pensionPer;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 医疗基数
     */
    private Integer medicalBase;
    /**
     * 医疗比率
     */
    private Float medicalPer;
    /**
     * 公积金基数
     */
    private Integer accumulationFundBase;
    /**
     * 公积金比率
     */
    private Float accumulationFundPer;
}