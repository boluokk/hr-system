package org.boluo.hr.employee.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.boluo.hr.employee.pojo.User;

/**
 * @author boluo
 * @date 2024/02/09
 */
@Mapper
public interface UserMapper {
    User findByWorkId(@Param("workId") String workId);

    String findWorkId(@Param("employeeId") int employeeId);
}
