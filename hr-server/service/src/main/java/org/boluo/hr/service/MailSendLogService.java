package org.boluo.hr.service;

import org.boluo.hr.mapper.MailSendLogMapper;
import org.boluo.hr.pojo.MailSendLog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 邮件日志 业务层
 *
 * @author 🍍
 * @date 2023/10/14
 */
@Service
public class MailSendLogService {

    @Resource
    private MailSendLogMapper mailSendLogMapper;

    /**
     * 新增邮件日志
     *
     * @param mailSendLog 日志对象信息
     * @return 结果
     */
    public boolean insert(MailSendLog mailSendLog) {
        return mailSendLogMapper.insertMailSendLog(mailSendLog) == 1;
    }

    /**
     * 查询所有邮件日志
     *
     * @return 邮件日志集合
     */
    public List<MailSendLog> selectAll() {
        return mailSendLogMapper.selectAll();
    }
}
