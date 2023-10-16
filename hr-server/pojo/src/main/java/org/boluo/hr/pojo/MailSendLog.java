package org.boluo.hr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 邮件日志 实体
 *
 * @author boluo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailSendLog {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 消息id
     */
    private Byte type;
    /**
     * 员工id
     */
    private Integer employeeId;
    /**
     * 状态: 0发送中，1发送成功，2发送失败
     */
    private Integer status;
    /**
     * 消息队列路由key
     */
    private String routeKey;
    /**
     * 消息队列 交换机
     */
    private String exchange;
    /**
     * 重试次数
     */
    private Integer count;

    /**
     * 第一次重试时间
     */
    private Date tryTime;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}