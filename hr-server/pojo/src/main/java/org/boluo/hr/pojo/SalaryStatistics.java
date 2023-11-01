package org.boluo.hr.pojo;

import lombok.Data;

/**
 * 工资统计 实体
 *
 * @author 🍍
 * @date 2023/10/31
 */
@Data
public class SalaryStatistics {
    /**
     * 账套名称
     */
    private String name;
    /**
     * 工资
     */
    private Integer salary;
}
