package org.boluo.hr.controller.sta.score;

import org.boluo.hr.annotation.Log;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.service.EmployeeScoreStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 员工积分统计信息
 *
 * @author 🍍
 * @date 2023/10/30
 */
@RestController
@RequestMapping("/sta/score")
public class EmployeeScoreStatisticsController {

    private final EmployeeScoreStatisticsService employeeScoreStatisticsService;

    @Autowired
    public EmployeeScoreStatisticsController(EmployeeScoreStatisticsService employeeScoreStatisticsService) {
        this.employeeScoreStatisticsService = employeeScoreStatisticsService;
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
    public RespBean findRewardPunishment(@PathVariable("days") Integer days) {
        if (days < 1) {
            days = 30;
        }
        return RespBean.ok(employeeScoreStatisticsService.selectRewardPunishment(days));
    }
}
