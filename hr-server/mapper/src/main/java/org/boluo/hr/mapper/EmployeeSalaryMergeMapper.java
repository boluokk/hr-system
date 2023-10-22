package org.boluo.hr.mapper;

import org.apache.ibatis.annotations.Param;
import org.boluo.hr.pojo.EmployeeSalaryMerge;
import org.boluo.hr.pojo.SalaryConfigView;

/**
 * 员工账套关系 数据层
 *
 * @author 🍍
 * @date 2023/10/21
 */
public interface EmployeeSalaryMergeMapper {
    int updateEmployeeSalaryMerge(EmployeeSalaryMerge employeeSalaryMerge);

    EmployeeSalaryMerge selectByEmployeeId(@Param("employeeId") Integer employeeId);

    int insertEmployeeSalaryMerge(EmployeeSalaryMerge employeeSalaryMerge);

}
