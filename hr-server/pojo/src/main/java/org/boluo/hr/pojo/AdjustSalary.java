package org.boluo.hr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 *  调薪 实体
 *
 * @author boluo
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdjustSalary {
    /**
     * 调薪id
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
     * 工资
     */
    private Integer salary;
    /**
     * 原因
     */
    private String reason;
    /**
     * 备注
     */
    private String remark;
}