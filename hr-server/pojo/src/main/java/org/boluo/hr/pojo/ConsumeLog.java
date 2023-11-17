package org.boluo.hr.pojo;

import lombok.Data;

/**
 * 消费记录 实体
 *
 * @author boluo
 * @date 2023/11/17
 */

@Data
public class ConsumeLog {
    /**
     * 主键
     */
    private String id;
    /**
     * 是否已消费
     */
    private byte consumed;
    /**
     * 唯一标识
     */
    private String uuid;
}
