package org.boluo.hr.controller.sta.pers;

import org.bluo.common.redis.util.RedisCache;
import org.boluo.hr.annotation.Log;
import org.boluo.hr.auto.CustomHttpSessionListener;
import org.boluo.hr.pojo.EmployeePageHeadCount;
import org.bluo.global.pojo.RespBean;
import org.boluo.hr.pojo.TableStaData;
import org.boluo.hr.service.HrInfoStatisticsService;
import org.boluo.hr.util.RedisKey;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.concurrent.TimeUnit;

/**
 * 人事信息统计信息
 *
 * @author 🍍
 * @date 2023/10/26
 */

@RestController
@RequestMapping("/sta/pers")
@Validated
public class HrInfoStatisticsController {

    @Resource
    private CustomHttpSessionListener sessionListener;
    @Resource
    private HrInfoStatisticsService hrInfoStatisticsService;
    @Resource
    private RedisCache redisCache;

    /**
     * 获取人事统计页头数据
     */
    @GetMapping("/header")
    @Log("获取人事统计页头数据")
    public RespBean findPageHeadData() {
        EmployeePageHeadCount employeePageHeadCount = new EmployeePageHeadCount();
        // 操作员在线个数
        employeePageHeadCount.setOnlineCount(sessionListener.getOnlineCount());
        // 操作员个数
        employeePageHeadCount.setOperatorCount(hrInfoStatisticsService.selectHrCount());
        // 操作员总操作次数
        employeePageHeadCount.setOperateCount(hrInfoStatisticsService.selectOperateCount());
        return RespBean.ok(employeePageHeadCount);
    }

    /**
     * 获取人事在线数据统计
     */
    @GetMapping("/online/{days}")
    @Log("获取人事在线数据统计")
    public RespBean findOnlineData(@Min(value = 1, message = "最少查询 1 天")
                                   @Max(value = 15, message = "最多查询 15 天")
                                   @PathVariable(value = "days") Integer days) {
        return RespBean.ok(redisCache.getWithPassThrough(RedisKey.HR_ONLINE_COUNT_KEY,
                days, TableStaData.class, hrInfoStatisticsService::selectRecentOnline, 15L, TimeUnit.MINUTES));
    }

    /**
     * 获取人事登录数据统计
     */
    @GetMapping("/login/{days}")
    @Log("获取人事登录数据统计")
    public RespBean findLoginData(@PathVariable
                                  @Max(value = 30, message = "最多查询 30 天")
                                  @Min(value = 1, message = "最少查询 1 天")
                                  Integer days) {
        return RespBean.ok(redisCache.getWithPassThrough(RedisKey.HR_LOGIN_COUNT_KEY,
                days, TableStaData.class, hrInfoStatisticsService::selectRecentLogin, 15L, TimeUnit.MINUTES));
    }
}
