package org.boluo.hr.service;

import org.boluo.hr.mapper.HrInfoStatisticsMapper;
import org.boluo.hr.pojo.HrInfoStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 在线人数 业务层
 *
 * @author 🍍
 * @date 2023/10/26
 */
@Service
public class HrInfoStatisticsService {
    private final HrInfoStatisticsMapper hrInfoStatisticsMapper;

    @Autowired
    public HrInfoStatisticsService(HrInfoStatisticsMapper hrInfoStatisticsMapper) {
        this.hrInfoStatisticsMapper = hrInfoStatisticsMapper;
    }

    /**
     * 新增在线人数
     *
     * @param hrInfoStatistics 在线人数信息
     * @return 结果
     */
    public boolean insertOnlineCount(HrInfoStatistics hrInfoStatistics) {
        return hrInfoStatisticsMapper.insertOnlineCount(hrInfoStatistics) == 1;
    }

    /**
     * 最近在线人数统计
     *
     * @return 在线人数信息统计
     */
    public List<HrInfoStatistics> selectRecentOnline(Integer days) {
        return hrInfoStatisticsMapper.selectRecentOnline(days);
    }

    /**
     * 查询操作员个数
     *
     * @return 操作员个数
     */
    public Integer selectHrCount() {
        return hrInfoStatisticsMapper.selectHrCount();
    }

    /**
     * 查询操作员操作次数
     *
     * @return 操作次数
     */
    public Integer selectOperateCount() {
        return hrInfoStatisticsMapper.selectOperateCount();
    }

    /**
     * 查询操作员登录统计
     *
     * @return 操作员登录统计集合
     */
    public List<HrInfoStatistics> selectRecentLogin(Integer days) {
        return hrInfoStatisticsMapper.selectRecentLogin(days);
    }

}
