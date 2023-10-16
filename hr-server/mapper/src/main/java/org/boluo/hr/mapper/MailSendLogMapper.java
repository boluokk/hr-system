package org.boluo.hr.mapper;

import org.boluo.hr.pojo.MailSendLog;

import java.util.List;


/**
 * 邮件日志 数据层
 *
 * @author boluo
 */
public interface MailSendLogMapper {
    /**
     * 新增邮件日志
     *
     * @param mailSendLog 邮件日志信息
     * @return 结果
     */
    int insertMailSendLog(MailSendLog mailSendLog);

    /**
     * 修改邮件日志
     *
     * @param mailSendLog 邮件日志信息
     * @return 结果
     */
    int updateByPrimaryKey(MailSendLog mailSendLog);

    /**
     * 查询所有邮件日志
     *
     * @return 邮件日志集合
     */
    List<MailSendLog> selectAll();
}