package org.boluo.hr.pojo;

import lombok.Data;

import java.util.List;

/**
 * 包装人事记录统计 实体
 *
 * @author 🍍
 * @date 2023/10/30
 */
@Data
public class WrapHrRecordStatistics {
    /**
     * 人事名称
     */
    private String name;
    /**
     * 人事记录统计信息
     */
    private List<HrRecordStatistics> hrRecordStatistics;
}
