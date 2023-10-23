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
public class EmployeeRemoveView {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 员工id
     */
    private Integer employeeId;
    /**
     * 员工信息
     */
    private Employee employee;
    /**
     * 调岗后的部门id
     */
    private Integer afterDepartmentId;
    /**
     * 调岗后的部门名称
     */
    private String afterDepartmentName;
    /**
     * 调岗后的职位id
     */
    private Integer afterJobId;
    /**
     * 调岗后的职位名称
     */
    private String afterJobName;
    /**
     * 调岗前的部门id
     */
    private Integer beforeDepartmentId;
    /**
     * 调岗前的部门名称
     */
    private String beforeDepartmentName;
    /**
     * 调岗前的职位id
     */
    private Integer beforeJobId;
    /**
     * 调岗前的职位名称
     */
    private String beforeJobName;
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
}