package org.boluo.hr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 工资月末 实体
 *
 * @author @1352955539(boluo)
 * @date 2021/3/25 - 22:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryMonth {
    /**
     * 主键
     */
    private int id;
    /**
     * 账套名称
     */
    private String salaryId;
    /**
     * 员工id
     */
    private Integer employeeId;
    /**
     * 时间
     */
    private Date time;
    /**
     * 账套名称
     */
    private String salaryName;
    /**
     * 所有工资
     */
    private int allSalary;
    /**
     * 备注
     */
    private String remark;
    /**
     * 员工信息
     */
    private Employee employee;
}
