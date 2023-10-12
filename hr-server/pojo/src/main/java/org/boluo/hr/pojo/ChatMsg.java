package org.boluo.hr.pojo;

import java.io.Serializable;
import java.util.Date;


/**
 * 聊天消息 实体
 *
 * @author boluo
 */
public class ChatMsg implements Serializable {
    /**
     * 内容
     */
    private String content;
    /**
     * 时间
     */
    private Date date;
    /**
     * 发送方
     */
    private String from;
    /**
     * 接收方
     */
    private String to;
    /**
     * 别名
     */
    private String nickName;

}
