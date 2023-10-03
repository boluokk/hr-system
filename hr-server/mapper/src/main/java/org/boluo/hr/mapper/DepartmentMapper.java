package com.boluo.hr.mapper;

import org.boluo.hr.pojo.Department;
import org.boluo.hr.pojo.DepartmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DepartmentMapper {
    int countByExample(DepartmentExample example);

    int deleteByExample(DepartmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    List<Department> selectByExample(DepartmentExample example);

    Department selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Department record, @Param("example") DepartmentExample example);

    int updateByExample(@Param("record") Department record, @Param("example") DepartmentExample example);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    List<Department> selectByParentId(@Param("id") Integer id);

    int lastInsertId();

    int selectCountParentId(@Param("id") Integer id);

    List<Department> getAllDepWithEnabledIsFalse();

    int deleteBydepPath(@Param("deppath") String path);

    List<Department> selectbyName(@Param("name") String name);

    List<Department> getalldepBy();
}