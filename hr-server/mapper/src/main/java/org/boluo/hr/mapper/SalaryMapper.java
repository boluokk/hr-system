package org.boluo.hr.mapper;

import org.boluo.hr.pojo.Employee;
import org.boluo.hr.pojo.Salary;
import org.boluo.hr.pojo.SalaryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SalaryMapper {
    int countByExample(SalaryExample example);

    int deleteByExample(SalaryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Salary record);

    int insertSelective(Salary record);

    List<Salary> selectByExample(SalaryExample example);

    Salary selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Salary record, @Param("example") SalaryExample example);

    int updateByExample(@Param("record") Salary record, @Param("example") SalaryExample example);

    int updateByPrimaryKeySelective(Salary record);

    int updateByPrimaryKey(Salary record);

    List<Salary> selectAll();

    int deleteMany(@Param("ids") Integer[] ids);

    int countSalaryWithEmp();

    List<Employee> selectEmpAndSalary(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    void insertEmpWithSal(@Param("eId") Integer eid, @Param("salId") Integer salId);
}