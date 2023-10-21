package org.boluo.hr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 培训 实体
 *
 * @author boluo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeTrain {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 员工id
     */
    private Integer employeeId;
    /**
     * 创建时间
     */
    private Date createData;
    /**
     * 培训内容
     */
    private String trainContent;
    /**
     * 备注
     */
    private String remark;
    /**
     * 员工信息
     */
    private Employee employee;
}