package org.boluo.hr.mapper;

import org.boluo.hr.pojo.Employeetrain;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmployeetrainMapper {
    int countByExample(EmployeetrainExample example);

    int deleteByExample(EmployeetrainExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Employeetrain record);

    int insertSelective(Employeetrain record);

    List<Employeetrain> selectByExample(EmployeetrainExample example);

    Employeetrain selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Employeetrain record, @Param("example") EmployeetrainExample example);

    int updateByExample(@Param("record") Employeetrain record, @Param("example") EmployeetrainExample example);

    int updateByPrimaryKeySelective(Employeetrain record);

    int updateByPrimaryKey(Employeetrain record);

    List<Employeetrain> selectAll();

    List<Employeetrain> selectAllWithEmpName();
}