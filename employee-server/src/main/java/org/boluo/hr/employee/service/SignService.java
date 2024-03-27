package org.boluo.hr.employee.service;

import org.boluo.hr.employee.pojo.EmployeeSign;

/**
 * @author boluo
 * @date 2024/02/09
 */
public interface SignService {
    boolean sign(int employeeId);

    EmployeeSign getOne(int employeeId);

    boolean signOut(int employeeId);

    int getCurrentMonthCount(int employeeId);
}
