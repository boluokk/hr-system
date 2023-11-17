package org.boluo.hr.controller.sta.score;

import org.bluo.common.redis.util.RedisCache;
import org.boluo.hr.annotation.Log;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.pojo.TableStaData;
import org.boluo.hr.service.EmployeeScoreStatisticsService;
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
 * 员工积分统计信息
 *
 * @author 🍍
 * @date 2023/10/30
 */
@RestController
@RequestMapping("/sta/score")
@Validated
public class EmployeeScoreStatisticsController {

    private final EmployeeScoreStatisticsService employeeScoreStatisticsService;
    private final RedisCache redisCache;

    @Autowired
    public EmployeeScoreStatisticsController(EmployeeScoreStatisticsService employeeScoreStatisticsService,
                                             RedisCache redisCache) {
        this.employeeScoreStatisticsService = employeeScoreStatisticsService;
        this.redisCache = redisCache;
    }

    /**
     * 获取页头数据
     */
    @GetMapping("/header")
    @Log("获取奖惩页头数据")
    public RespBean findPageHerder() {
        return RespBean.ok(employeeScoreStatisticsService.selectHeaderData());
    }

    /**
     * 获取奖惩统计
     */
    @GetMapping("/rewardPh/{days}")
    @Log("获取奖惩统计")
    public RespBean findRewardPunishment(@Max(value = 30, message = "最大天数为30天")
                                         @Min(value = 1, message = "最小天数为1天")
                                         @PathVariable("days") Integer days) {
        return RespBean.ok(redisCache.getWithPassThrough(RedisKey.HR_REWARD_PUNISHMENT_COUNT_KEY,
                days, TableStaData.class, employeeScoreStatisticsService::selectRewardPunishment, 15L,
                TimeUnit.MINUTES));
    }
}
