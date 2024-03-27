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
public class InsertAdjustSalary {
    /**
     * 员工id
     */
    private Integer employeeId;
    /**
     * 创建时间
     */
    @NotNull(message = "创建时间不能为空")
    private Date createDate;
    /**
     * 工资
     */
    @NotNull(message = "工资不能为空")
    private Integer salary;
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
