package org.boluo.hr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 工资账套设置视图 实体
 *
 * @author 🍍
 * @date 2023/10/21
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryConfigView {
    /**
     * 员工id
     */
    private Integer employeeId;
    /**
     * 员工名称
     */
    private String employeeName;
    /**
     * 员工工号
     */
    private String workId;
    /**
     * 员工邮箱
     */
    private String email;
    /**
     * 员工手机号
     */
    private String phone;
    /**
     * 所属部门id
     */
    private Integer departmentId;
    /**
     * 所属部门名称
     */
    private String departmentName;
    /**
     * 账套信息
     */
    private Salary salary;
}
