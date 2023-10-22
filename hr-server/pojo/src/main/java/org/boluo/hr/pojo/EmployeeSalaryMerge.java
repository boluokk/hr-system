package org.boluo.hr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 员工账套关系 实体
 *
 * @author 🍍
 * @date 2023/10/21
 */

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeSalaryMerge {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 员工id
     */
    private Integer employeeId;
    /**
     * 账套id
     */
    private Integer salaryId;
}
