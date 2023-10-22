package org.boluo.hr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 工资表视图 实体
 *
 * @author 🍍
 * @date 2023/10/22
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SalaryTableView {
    /**
     * 员工名
     */
    private String employeeName;
    /**
     * 员工号
     */
    private String workId;
    /**
     * 部门名
     */
    private String departmentName;
    /**
     * 职称名
     */
    private String jobLevelName;
    /**
     * 罚款
     */
    private Integer punishment;
    /**
     * 奖励
     */
    private Integer reward;
    /**
     * 个人所得税
     */
    private Integer tax;
    /**
     * 实发工资
     */
    private Integer wagesPayable;
    /**
     * 账套信息
     */
    private Salary salary;
}
