package org.boluo.hr.service;

import org.boluo.hr.mapper.HrRecordStatisticsMapper;
import org.boluo.hr.pojo.TableStaData;
import org.boluo.hr.pojo.WrapHrRecordStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 人事记录统计 业务层
 *
 * @author 🍍
 * @date 2023/10/30
 */
@Service
public class HrRecordStatisticsService {
    private final HrRecordStatisticsMapper hrRecordStatisticsMapper;

    @Autowired
    public HrRecordStatisticsService(HrRecordStatisticsMapper hrRecordStatisticsMapper) {
        this.hrRecordStatisticsMapper = hrRecordStatisticsMapper;
    }

    /**
     * 获取人事记录
     *
     * @param days 天数
     * @return 人事记录统计集合
     */
    public TableStaData<WrapHrRecordStatistics> selectAll(Integer days) {
        return new TableStaData<>(hrRecordStatisticsMapper.selectAll(days));
    }
}
