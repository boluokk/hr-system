package org.boluo.hr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 系统消息 实体
 *
 * @author boluo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemMessage {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 消息id
     */
    private Integer messageId;
    /**
     * 消息类型: 0表示群发
     */
    private Integer type;
    /**
     * 人事id: 这条消息给谁
     */
    private Integer hrId;
    /**
     * 状态: 0 未读 1 已读
     */
    private Integer state;
}