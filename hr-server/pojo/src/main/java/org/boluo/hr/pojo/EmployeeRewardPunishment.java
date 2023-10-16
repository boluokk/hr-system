package org.boluo.hr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 员工奖惩 实体
 *
 * @author boluo
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRewardPunishment {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 员工id
     */
    private Integer employeeId;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 原因
     */
    private String reason;
    /**
     * 分数
     */
    private Integer point;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 备注
     */
    private String remark;

}