package org.boluo.hr.mapper;

import org.boluo.hr.pojo.HrInfoStatistics;

import java.util.List;

/**
 * 人事在线统计 数据层
 *
 * @author 🍍
 * @date 2023/10/26
 */
public interface HrInfoStatisticsMapper {

    /**
     * 查询最近在线统计
     *
     * @return 人事在线统计集合
     */
    List<HrInfoStatistics> selectRecentOnline(Integer days);

    /**
     * 新增在线人数
     *
     * @param hrInfoStatistics 人事统计信息
     * @return 结果
     */
    int insertOnlineCount(HrInfoStatistics hrInfoStatistics);

    /**
     * 获取操作员个数
     *
     * @return 操作员个数
     */
    int selectHrCount();

    /**
     * 获取操作员操作次数
     *
     * @return 操作次数
     */
    int selectOperateCount();

    /**
     * 最近操作员登录统计(默认7天)
     *
     * @return 人事登录统计信息
     */
    List<HrInfoStatistics> selectRecentLogin(Integer days);
}
