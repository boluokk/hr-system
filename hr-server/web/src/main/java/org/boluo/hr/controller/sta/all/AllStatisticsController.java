package org.boluo.hr.controller.sta.all;

import org.boluo.hr.annotation.Log;
import org.bluo.global.pojo.RespBean;
import org.boluo.hr.service.AllStatisticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 综合统计信息
 *
 * @author 🍍
 * @date 2023/10/31
 */
@RestController
@RequestMapping("/sta/all")
public class AllStatisticsController {

    @Resource
    private AllStatisticsService allStatisticsService;

    /**
     * 获取综合信息统计页头
     */
    @GetMapping("/header/count")
    @Log("获取综合信息统计页头")
    public RespBean findHeaderCount() {
        return RespBean.ok(allStatisticsService.selectHeaderCount());
    }

    /**
     * 获取工资统计数据
     */
    @GetMapping("/salary")
    @Log("获取工资统计数据")
    public RespBean findSalarySta() {
        return RespBean.ok(allStatisticsService.selectSalaryStatistics());
    }

    /**
     * 获取部门结构
     */
    @GetMapping("/department/struct")
    @Log("查询部门结构")
    public RespBean findDepartmentStruct() {
        return RespBean.ok(allStatisticsService.selectDepartmentStruct());
    }

}
