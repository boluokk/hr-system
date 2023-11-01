package org.boluo.hr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 人事信息统计 实体
 *
 * @author 🍍
 * @date 2023/10/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HrInfoStatistics {
    /**
     * 人数
     */
    private Integer count;
    /**
     * 时间
     */
    private Date time;
}
