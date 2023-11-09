package org.boluo.hr.pojo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author boluo
 * @date 2023/11/08
 */
@Data
public class InsertEmployeeRemove {
    /**
     * 员工id
     */
    private Integer employeeId;
    /**
     * 调岗后的部门id
     */
    @NotNull(message = "调岗后的部门id不能为空")
    @Min(value = 1, message = "调岗后的部门id不能小于1")
    private Integer afterDepartmentId;
    /**
     * 调岗后的职位id
     */
    @NotNull(message = "调岗后的职位id不能为空")
    @Min(value = 1, message = "调岗后的职位id不能小于1")
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
    @NotNull(message = "调岗时间不能为空")
    private Date removeDate;
    /**
     * 原因
     */
    @NotNull(message = "原因不能为空")
    private String reason;
    /**
     * 备注
     */
    private String remark;
}
