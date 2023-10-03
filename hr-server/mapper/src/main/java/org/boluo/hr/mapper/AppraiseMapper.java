package org.boluo.hr.mapper;

import org.boluo.hr.pojo.Appraise;
import org.boluo.hr.pojo.AppraiseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;


public interface AppraiseMapper {
    int countByExample(AppraiseExample example);

    int deleteByExample(AppraiseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Appraise record);

    int insertSelective(Appraise record);

    List<Appraise> selectByExample(AppraiseExample example);

    Appraise selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Appraise record, @Param("example") AppraiseExample example);

    int updateByExample(@Param("record") Appraise record, @Param("example") AppraiseExample example);

    int updateByPrimaryKeySelective(Appraise record);

    int updateByPrimaryKey(Appraise record);

    List<Appraise> selectAll();
}