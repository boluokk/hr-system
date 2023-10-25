package org.boluo.hr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 操作日志 实体
 *
 * @author boluo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperatorLog {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 事件内容(记录接口)
     */
    private String event;
    /**
     * 人事id（操作员）
     */
    private Integer hrId;
    /**
     * 操作地址
     */
    private String address;
    /**
     * 内容
     */
    private String content;
    /**
     * 类型
     */
    private String type;
}