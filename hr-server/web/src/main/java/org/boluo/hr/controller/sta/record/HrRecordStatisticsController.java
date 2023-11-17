package org.boluo.hr.controller.sta.record;

import org.bluo.common.redis.util.RedisCache;
import org.boluo.hr.annotation.Log;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.pojo.TableStaData;
import org.boluo.hr.service.HrRecordStatisticsService;
import org.boluo.hr.util.RedisKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.concurrent.TimeUnit;

/**
 * 人事记录统计信息
 *
 * @author 🍍
 * @date 2023/10/29
 */

@RestController
@RequestMapping("/sta/record")
@Validated
public class HrRecordStatisticsController {
    private final HrRecordStatisticsService hrRecordStatisticsService;
    private final RedisCache redisCache;

    @Autowired
    public HrRecordStatisticsController(HrRecordStatisticsService hrRecordStatisticsService, RedisCache redisCache) {
        this.hrRecordStatisticsService = hrRecordStatisticsService;
        this.redisCache = redisCache;
    }

    /**
     * 获取人事记录统计
     */
    @GetMapping("/all/{days}")
    @Log("获取人事记录统计")
    public RespBean findHrRecordData(@Max(value = 30, message = "最大天数为30天")
                                     @Min(value = 1, message = "最小天数为1天")
                                     @PathVariable("days") Integer days) {
        return RespBean.ok(redisCache.getWithPassThrough(RedisKey.HR_RECORD_COUNT_KEY,
                days, TableStaData.class, hrRecordStatisticsService::selectAll, 15L, TimeUnit.MINUTES));
    }
}
