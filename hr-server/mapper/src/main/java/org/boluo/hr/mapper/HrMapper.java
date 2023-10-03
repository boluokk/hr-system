package org.boluo.hr.mapper;

import org.boluo.hr.pojo.Hr;
import org.boluo.hr.pojo.HrExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HrMapper {
    int countByExample(HrExample example);

    int deleteByExample(HrExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Hr record);

    int insertSelective(Hr record);

    List<Hr> selectByExample(HrExample example);

    Hr selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Hr record, @Param("example") HrExample example);

    int updateByExample(@Param("record") Hr record, @Param("example") HrExample example);

    int updateByPrimaryKeySelective(Hr record);

    int updateByPrimaryKey(Hr record);

    Hr loadByUserName(String username);

    List<Hr> getRoleWithHrId(Integer id);

    List<Hr> selectAllExceptCurrentHr(@Param("id") Integer id);

    List<Hr> selectAll();

    List<Hr> selectHrByName(@Param("hrName") String hrName);
}