package org.boluo.hr.employee.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.boluo.hr.employee.pojo.EmployeeSign;

/**
 * @author boluo
 * @date 2024/02/09
 */
@Mapper
public interface EmployeeSignMapper {
    EmployeeSign findSign(@Param("employeeId") int employeeId, @Param("currentTime") String date);

    int insert(EmployeeSign employeeSign);

    int updateSignOut(@Param("employeeId") int employeeId, @Param("signOutTime") String signOutTime);

    int selectCurMonCnt(@Param("employeeId") int employeeId);
}
