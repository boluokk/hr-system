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
public class InsertEmployeeTrain {
    /**
     * 员工id
     */
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
