package org.boluo.hr.mapper;

import org.boluo.hr.pojo.WrapHrRecordStatistics;

import java.util.List;

/**
 * 人事记录统计 数据层
 *
 * @author 🍍
 * @date 2023/10/30
 */
public interface HrRecordStatisticsMapper {
    /**
     * 查询所有人事记录统计
     *
     * @param days 天数
     * @return 人事记录统计集合
     */
    List<WrapHrRecordStatistics> selectAll(Integer days);
}
