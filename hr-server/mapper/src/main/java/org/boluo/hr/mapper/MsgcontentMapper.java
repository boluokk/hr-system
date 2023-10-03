package org.boluo.hr.mapper;

import org.boluo.hr.pojo.Msgcontent;
import org.boluo.hr.pojo.MsgcontentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MsgcontentMapper {
    int countByExample(MsgcontentExample example);

    int deleteByExample(MsgcontentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Msgcontent record);

    int insertSelective(Msgcontent record);

    List<Msgcontent> selectByExample(MsgcontentExample example);

    Msgcontent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Msgcontent record, @Param("example") MsgcontentExample example);

    int updateByExample(@Param("record") Msgcontent record, @Param("example") MsgcontentExample example);

    int updateByPrimaryKeySelective(Msgcontent record);

    int updateByPrimaryKey(Msgcontent record);
}