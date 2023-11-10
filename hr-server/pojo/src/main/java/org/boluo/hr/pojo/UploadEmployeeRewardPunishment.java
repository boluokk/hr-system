package org.boluo.hr.pojo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @author boluo
 * @date 2023/11/10
 */
@Data
public class UploadEmployeeRewardPunishment {
    /**
     * 主键
     */
    @NotNull(message = "id不能为空")
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
    @NotNull(message = "备注不能为空")
    @Pattern(regexp = "大功|小功|嘉奖", message = "备注只能是大功小功嘉奖")
    private String remark;
}
