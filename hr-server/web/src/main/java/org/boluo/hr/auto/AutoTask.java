package org.boluo.hr.auto;


import lombok.extern.slf4j.Slf4j;
import org.boluo.hr.pojo.HrInfoStatistics;
import org.boluo.hr.service.HrInfoStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 定时任务
 *
 * @author 🍍
 * @date 2023/10/26
 */

@Component
@Slf4j
public class AutoTask {

    private final HrInfoStatisticsService hrInfoStatisticsService;

    private final CustomHttpSessionListener customHttpSessionListener;

    @Autowired
    public AutoTask(HrInfoStatisticsService hrInfoStatisticsService, SessionRegistry sessionRegistry, CustomHttpSessionListener customHttpSessionListener) {
        this.hrInfoStatisticsService = hrInfoStatisticsService;
        this.customHttpSessionListener = customHttpSessionListener;
    }

    // 0,10,20,30,40,50 * * * * ?
    @Scheduled(cron = "0 0,30 * * * ?")
    public void recordOnlineCount() {
        HrInfoStatistics hrInfoStatistics = new HrInfoStatistics();
        hrInfoStatistics.setCount(customHttpSessionListener.getOnlineCount());
        hrInfoStatistics.setTime(new Date());
        if (!hrInfoStatisticsService.insertOnlineCount(hrInfoStatistics)) {
            log.info("新增在线人数失败：{}", hrInfoStatistics);
        }
    }
}
