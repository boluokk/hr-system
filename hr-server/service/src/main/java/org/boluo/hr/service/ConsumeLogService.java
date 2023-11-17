package org.boluo.hr.service;

import org.boluo.hr.mapper.ConsumeLogMapper;
import org.boluo.hr.pojo.ConsumeLog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 消费记录 业务层
 *
 * @author boluo
 * @date 2023/11/17
 */
@Service
public class ConsumeLogService {
    @Resource
    private ConsumeLogMapper consumeLogMapper;

    /**
     * 新增消费记录
     *
     * @param log 消费记录
     * @return 结果
     */
    public boolean insert(ConsumeLog log) {
        return consumeLogMapper.insert(log) == 1;
    }
}
