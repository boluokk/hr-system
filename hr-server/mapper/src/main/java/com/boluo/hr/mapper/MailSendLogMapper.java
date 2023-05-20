package com.boluo.hr.mapper;

import com.boluo.hr.pojo.MailSendLog;
import com.boluo.hr.pojo.MailSendLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MailSendLogMapper {
    int countByExample(MailSendLogExample example);

    int deleteByExample(MailSendLogExample example);

    int insert(MailSendLog record);

    int insertSelective(MailSendLog record);

    List<MailSendLog> selectByExample(MailSendLogExample example);

    int updateByExampleSelective(@Param("record") MailSendLog record, @Param("example") MailSendLogExample example);

    int updateByExample(@Param("record") MailSendLog record, @Param("example") MailSendLogExample example);
}