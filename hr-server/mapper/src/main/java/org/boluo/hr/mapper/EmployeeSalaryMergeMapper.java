package org.boluo.hr.mapper;

import org.apache.ibatis.annotations.Param;
import org.boluo.hr.pojo.EmployeeSalaryMerge;

/**
 * 员工账套关系 数据层
 *
 * @author 🍍
 * @date 2023/10/21
 */
public interface EmployeeSalaryMergeMapper {
    /**
     * 修改员工账套关系
     *
     * @param employeeSalaryMerge 员工账套关系
     * @return 结果
     */
    int updateEmployeeSalaryMerge(EmployeeSalaryMerge employeeSalaryMerge);

    /**
     * 通过员工id查询员工账套关系
     *
     * @param employeeId 员工id
     * @return 结果
     */
    EmployeeSalaryMerge selectByEmployeeId(@Param("employeeId") Integer employeeId);

    /**
     * 新增员工账套关系
     *
     * @param employeeSalaryMerge 员工账套关系
     * @return 结果
     */
    int insertEmployeeSalaryMerge(EmployeeSalaryMerge employeeSalaryMerge);

    /**
     * 删除员工账套关系
     *
     * @param employeeId 员工id
     * @return 结果
     */
    int deleteByEmployeeId(@Param("employeeId") Integer employeeId);

}
