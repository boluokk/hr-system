package com.boluo.hr.mapper;

import com.boluo.hr.pojo.Employee;
import com.boluo.hr.pojo.EmployeeExample;
import java.util.List;

import com.boluo.hr.pojo.Nation;
import org.apache.ibatis.annotations.Param;

public interface EmployeeMapper {
    int countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectByExample(EmployeeExample example);

    Employee selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    List<Employee> selectAllByPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    int selectCount();

    List<Employee> selectByEmpName(@Param("empName") String empName);

    Integer selectMaxWorkID();

    int deleteByManyID(@Param("ids") Integer[] ids);

    List<Nation> selectByNation();

    int insertMany(@Param("list") List<Employee> employees);

    List<Employee> selectByTipSerch(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("employee") Employee employee);

    int selectByTipCount(@Param("employee") Employee employee);

    Employee selectByEmpID(@Param("id") Integer id);

}