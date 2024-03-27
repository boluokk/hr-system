package org.boluo.hr.employee.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author boluo
 * @date 2024/02/09
 */
@Data
public class EmployeeSign {
    private int employeeId;
    private Date signTime;
    private Date signOutTime;
}
