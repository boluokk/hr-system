package org.boluo.hr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * 消息内容 实体
 *
 * @author boluo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageContent {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 标题
     */
    private String title;
    /**
     * 消息内容
     */
    private String message;
    /**
     * 创建时间
     */
    private Date createDate;
}