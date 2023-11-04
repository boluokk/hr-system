package org.boluo.hr.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 操作日志页头数据 实体
 *
 * @author 🍍
 * @date 2023/11/01
 */
@Data
public class OperatorHeaderCount {
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 操作内容
     */
    private String content;
    /**
     * 操作人
     */
    private String operatorName;
    /**
     * 操作日志数量
     */
    private Integer operatorLogCount;
}
