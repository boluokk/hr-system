package org.boluo.hr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 基础调岗 实体
 *
 * @author 🍍
 * @date 2023/10/20
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEmployeeRemove {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 员工id
     */
    private Integer employeeId;
    /**
     * 调岗后的部门id
     */
    private Integer afterDepartmentId;
    /**
     * 调岗后的职位id
     */
    private Integer afterJobId;
    /**
     * 调岗前的部门id
     */
    private Integer beforeDepartmentId;
    /**
     * 调岗前的职位id
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
}
