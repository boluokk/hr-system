package org.boluo.hr.mapper;

import org.boluo.hr.pojo.FlywaySchemaHistory;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FlywaySchemaHistoryMapper {

    int deleteByPrimaryKey(Integer installedRank);

    int insert(FlywaySchemaHistory record);

    int insertSelective(FlywaySchemaHistory record);

    FlywaySchemaHistory selectByPrimaryKey(Integer installedRank);

    int updateByPrimaryKeySelective(FlywaySchemaHistory record);

    int updateByPrimaryKey(FlywaySchemaHistory record);
}