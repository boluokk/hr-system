package org.boluo.hr.mapper;

import org.boluo.hr.pojo.ConsumeLog;

/**
 * @author boluo
 * @date 2023/11/17
 */
public interface ConsumeLogMapper {
    /**
     * 新增
     *
     * @param log 消费记录
     * @return 结果
     */
    int insert(ConsumeLog log);
}
