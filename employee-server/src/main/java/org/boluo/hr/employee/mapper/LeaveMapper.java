package org.boluo.hr.employee.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.boluo.hr.employee.pojo.Leave;

import java.util.List;

/**
 * @author boluo
 * @version 1.0.0
 * @date 2024/02/09
 */
@Mapper
public interface LeaveMapper {
    List<Leave> findAll(@Param("employeeId") int employeeId);

    int delete(int id);

    int insert(Leave leave);
}
