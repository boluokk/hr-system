package org.boluo.hr.auto;


import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.boluo.hr.pojo.CustomConfig;
import org.boluo.hr.pojo.HrInfoStatistics;
import org.boluo.hr.service.HrInfoStatisticsService;
import org.boluo.hr.util.CustomFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
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
    private final CustomFileUtil customFileUtil;

    @Autowired
    public AutoTask(HrInfoStatisticsService hrInfoStatisticsService,
                    CustomHttpSessionListener customHttpSessionListener,
                    CustomConfig customConfig, CustomFileUtil customFileUtil1) {
        this.hrInfoStatisticsService = hrInfoStatisticsService;
        this.customHttpSessionListener = customHttpSessionListener;
        this.customFileUtil = customFileUtil1;
    }

    /**
     * 在线人数统计录入数据库
     */
    @Scheduled(cron = "0 0,30 * * * ?")
    public void recordOnlineCount() {
        HrInfoStatistics hrInfoStatistics = new HrInfoStatistics();
        hrInfoStatistics.setCount(customHttpSessionListener.getOnlineCount());
        hrInfoStatistics.setTime(new Date());
        if (!hrInfoStatisticsService.insertOnlineCount(hrInfoStatistics)) {
            log.info("新增在线人数失败：{}", hrInfoStatistics);
        }
    }


    /**
     * 备份数据库
     */
    @Scheduled(cron = "${custom.config.auto_backup_cron}")
    public void backupDatabase() {
        File[] ls = FileUtil.ls(customFileUtil.getEnhanceServerBackupFilePath("/history"));
        // 删除过期文件
        for (File file : ls) {
            long l = file.lastModified();
            // 最少会保存10个备份
            // 需要加锁, 如果在备份中，不应该删除
            if (l < customFileUtil.getBackupExpire() && ls.length > 10) {
                FileUtil.del(file);
            }
        }
        try {
            String sqlFileName = customFileUtil.getCurrentFormatTime("yyyy-MM-dd_HH-mm-ss") + ".sql";
            String preFilePath = "/history/";
            Process dumpSql = Runtime.getRuntime()
                    .exec(customFileUtil.getDumpDatabaseCommand(preFilePath + sqlFileName));
            if (dumpSql.waitFor() != 0) {
                log.warn("定期备份数据库文件失败");
            }
        } catch (Exception e) {
            log.warn("定期备份数据库文件失败");
        }
    }
}
