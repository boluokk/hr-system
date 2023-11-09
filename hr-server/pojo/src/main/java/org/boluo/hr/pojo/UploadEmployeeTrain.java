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
public class UploadEmployeeTrain {
    /**
     * 主键
     */
    @NotNull(message = "id不能为空")
    private Integer id;
    /**
     * 员工id
     */
    @NotNull(message = "员工id不能为空")
    @Min(value = 1, message = "员工id不能小于1")
    private Integer employeeId;
    /**
     * 创建时间
     */
    @NotNull(message = "创建时间不能为空")
    private Date createData;
    /**
     * 培训内容
     */
    @NotNull(message = "培训内容不能为空")
    private String trainContent;
    /**
     * 备注
     */
    private String remark;
}
