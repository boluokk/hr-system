package org.boluo.hr.mapper;

import org.boluo.hr.pojo.MessageContent;

/**
 * 消息内容 数据层
 *
 * @author boluo
 */
public interface MessageContentMapper {
    /**
     * 删除消息内容
     *
     * @param id 消息内容id
     * @return 结果
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增消息内容
     *
     * @param messageContent 消息内容信息
     * @return 结果
     */
    int insertMessageContent(MessageContent messageContent);

    /**
     * 查询消息内容
     *
     * @param id 消息内容id
     * @return 消息内容信息
     */
    MessageContent selectByPrimaryKey(Integer id);

    /**
     * 修改消息内容
     *
     * @param messageContent 消息内容
     * @return 结果
     */
    int updateByPrimaryKey(MessageContent messageContent);
}