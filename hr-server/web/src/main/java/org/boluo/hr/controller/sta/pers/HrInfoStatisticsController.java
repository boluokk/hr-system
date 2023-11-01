package org.boluo.hr.controller.sta.pers;

import org.boluo.hr.annotation.Log;
import org.boluo.hr.auto.CustomHttpSessionListener;
import org.boluo.hr.pojo.EmployeePageHeadCount;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.service.HrInfoStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 人事信息统计信息
 *
 * @author 🍍
 * @date 2023/10/26
 */

@RestController
@RequestMapping("/sta/pers")
public class HrInfoStatisticsController {

    private final CustomHttpSessionListener sessionListener;
    private final HrInfoStatisticsService hrInfoStatisticsService;

    @Autowired
    public HrInfoStatisticsController(CustomHttpSessionListener sessionListener,
                                      HrInfoStatisticsService hrInfoStatisticsService) {
        this.sessionListener = sessionListener;
        this.hrInfoStatisticsService = hrInfoStatisticsService;
    }


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
    public RespBean findOnlineData(@PathVariable(value = "days") Integer days) {
        // 默认最近 1 天
        if (days < 1) {
            days = 1;
        }
        return RespBean.ok(hrInfoStatisticsService.selectRecentOnline(days));
    }

    /**
     * 获取人事登录数据统计
     */
    @GetMapping("/login/{days}")
    @Log("获取人事登录数据统计")
    public RespBean findLoginData(@PathVariable(value = "days") Integer days) {
        // 默认最近 7 天
        if (days < 1) {
            days = 7;
        }
        return RespBean.ok(hrInfoStatisticsService.selectRecentLogin(days));
    }
}
