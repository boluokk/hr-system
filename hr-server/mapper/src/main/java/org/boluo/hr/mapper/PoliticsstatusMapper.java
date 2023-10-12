package org.boluo.hr.mapper;

import org.boluo.hr.pojo.Politicsstatus;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PoliticsstatusMapper {
    int countByExample(PoliticsstatusExample example);

    int deleteByExample(PoliticsstatusExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Politicsstatus record);

    int insertSelective(Politicsstatus record);

    List<Politicsstatus> selectByExample(PoliticsstatusExample example);

    Politicsstatus selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Politicsstatus record, @Param("example") PoliticsstatusExample example);

    int updateByExample(@Param("record") Politicsstatus record, @Param("example") PoliticsstatusExample example);

    int updateByPrimaryKeySelective(Politicsstatus record);

    int updateByPrimaryKey(Politicsstatus record);

    List<Politicsstatus> selectAllPlitic();
}