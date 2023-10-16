package org.boluo.hr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 调岗 实体
 *
 * @author boluo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRemove {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 员工id
     */
    private Integer employeeId;
    /**
     * 调岗后的部门
     */
    private Integer afterDepartmentId;
    /**
     * 调岗后的职位
     */
    private Integer afterJobId;
    /**
     * 调岗前的部门
     */
    private Integer beforeDepartmentId;
    /**
     * 调岗前的职位
     */
    private Integer beforeJobId;
    /**
     * 调岗时间
     */
    private Date removeDate;
    /**
     * 原因
     */
    private String reason;
    /**
     * 备注
     */
    private String remark;
    /**
     * 员工信息
     */
    private Employee employee;
}