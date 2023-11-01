package org.boluo.hr.controller.sta.record;

import org.boluo.hr.annotation.Log;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.service.HrRecordStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 人事记录统计信息
 *
 * @author 🍍
 * @date 2023/10/29
 */

@RestController
@RequestMapping("/sta/record")
public class HrRecordStatisticsController {
    private final HrRecordStatisticsService hrRecordStatisticsService;

    @Autowired
    public HrRecordStatisticsController(HrRecordStatisticsService hrRecordStatisticsService) {
        this.hrRecordStatisticsService = hrRecordStatisticsService;
    }

    /**
     * 获取人事记录统计
     */
    @GetMapping("/all/{days}")
    @Log("获取人事记录统计")
    public RespBean findHrRecordData(@PathVariable("days") Integer days) {
        if (days < 1) {
            days = 3;
        }
        return RespBean.ok(hrRecordStatisticsService.selectAll(days));
    }
}
