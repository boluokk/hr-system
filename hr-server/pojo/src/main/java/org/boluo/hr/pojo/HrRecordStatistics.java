package org.boluo.hr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 人事记录统计 实体
 *
 * @author 🍍
 * @date 2023/10/30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HrRecordStatistics {
    /**
     * 人事名称
     */
    private String name;
    /**
     * 时间
     */
    private Date time;
    /**
     * 操作次数
     */
    private Integer count;
}
