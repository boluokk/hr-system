package org.boluo.mailserver.service;

import org.boluo.mailserver.mapper.ConsumeLogMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 邮件消费 业务层
 *
 * @author boluo
 * @date 2023/11/17
 */
@Service
public class ConsumeLogService {

    @Resource
    private ConsumeLogMapper consumeLogMapper;

    /**
     * 设置已消费
     *
     * @param uuid 唯一标识
     * @return 结果
     */
    public boolean uploadToConsumed(String uuid) {
        return consumeLogMapper.uploadConsumed(uuid) > 0;
    }
}
