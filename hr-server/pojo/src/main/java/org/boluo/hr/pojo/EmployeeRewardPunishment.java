package org.boluo.hr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @NotNull(message = "员工id不能为空")
    @Min(value = 1, message = "员工id必须大于0")
    private Integer employeeId;
    /**
     * 创建时间
     */
    @NotNull(message = "创建时间不能为空")
    private Date createDate;
    /**
     * 原因
     */
    @NotNull(message = "原因不能为空")
    private String reason;
    /**
     * 分数
     */
    @NotNull(message = "分数不能为空")
    private Integer point;
    /**
     * 类型
     */
    @NotNull(message = "类型不能为空")
    private Integer type;
    /**
     * 备注
     */
    private String remark;
    /**
     * 员工信息
     */
    private UploadEmployee employee;
}