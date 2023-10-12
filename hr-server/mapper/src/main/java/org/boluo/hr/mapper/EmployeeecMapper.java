package org.boluo.hr.mapper;

import org.boluo.hr.pojo.Employeeec;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmployeeecMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Employeeec record);

    int insertSelective(Employeeec record);

    Employeeec selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employeeec record);

    int updateByPrimaryKey(Employeeec record);

    List<Employeeec> selectAll();

    Integer selectByWorkId(@Param("workId") String workId);

}