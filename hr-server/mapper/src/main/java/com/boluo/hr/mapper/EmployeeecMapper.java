package com.boluo.hr.mapper;

import com.boluo.hr.pojo.Employeeec;
import com.boluo.hr.pojo.EmployeeecExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmployeeecMapper {
    int countByExample(EmployeeecExample example);

    int deleteByExample(EmployeeecExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Employeeec record);

    int insertSelective(Employeeec record);

    List<Employeeec> selectByExample(EmployeeecExample example);

    Employeeec selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Employeeec record, @Param("example") EmployeeecExample example);

    int updateByExample(@Param("record") Employeeec record, @Param("example") EmployeeecExample example);

    int updateByPrimaryKeySelective(Employeeec record);

    int updateByPrimaryKey(Employeeec record);

    List<Employeeec> selectAll();

    Integer selectEid(@Param("workId") String workId);
}