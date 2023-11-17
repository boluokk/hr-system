package org.boluo.mailserver.mapper;

/**
 * 邮件消费记录 数据层
 *
 * @author boluo
 * @date 2023/11/17
 */
public interface ConsumeLogMapper {
    /**
     * 更新消费状态
     *
     * @param uuid 消费记录的uuid
     * @return 结果
     */
    int uploadConsumed(String uuid);
}
