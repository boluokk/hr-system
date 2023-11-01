package org.boluo.hr.pojo;

import lombok.Data;

/**
 * 综合统计数据页头 实体
 *
 * @author 🍍
 * @date 2023/10/31
 */
@Data
public class AllStatisticsHeader {
    /**
     * 员工人数
     */
    private Integer employeeCount;
    /**
     * 部门数
     */
    private Integer departmentCount;
    /**
     * 职位数
     */
    private Integer jobLevelCount;
    /**
     * 角色数
     */
    private Integer roleCount;
}
