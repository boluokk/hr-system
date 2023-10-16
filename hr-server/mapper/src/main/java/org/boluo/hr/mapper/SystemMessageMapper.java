package org.boluo.hr.mapper;

import org.boluo.hr.pojo.SystemMessage;

/**
 * 系统消息 数据层
 *
 * @author boluo
 */
public interface SystemMessageMapper {

    /**
     * 删除系统消息
     *
     * @param id 系统消息id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增系统消息
     *
     * @param systemMessage 系统消息信息
     * @return 结果
     */
    int insertSystemMessage(SystemMessage systemMessage);

    /**
     * 查询系统消息
     *
     * @param id 系统消息id
     * @return 系统消息信息
     */
    SystemMessage selectByPrimaryKey(Integer id);

    /**
     * 修改系统消息
     *
     * @param systemMessage 系统消息信息
     * @return 结果
     */
    int updateByPrimaryKey(SystemMessage systemMessage);

}