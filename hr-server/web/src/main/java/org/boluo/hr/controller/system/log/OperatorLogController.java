package org.boluo.hr.controller.system.log;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.boluo.hr.annotation.Log;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.service.OperatorLogService;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Min;

/**
 * 操作日志管理
 *
 * @author 🍍
 * @date 2023/11/01
 */

@RequestMapping("/sys/log")
@RestController
@Validated
public class OperatorLogController {

    @Resource
    private OperatorLogService operatorLogService;


    /**
     * 获取分页操作日志
     */
    @Log("获取分页操作日志")
    @GetMapping("/all/{pageNum}/{pageSize}")
    public RespBean findLogByPage(@Min(value = 1, message = "页码不能小于1")
                                  @PathVariable("pageNum") Integer pageNum,
                                  @Range(min = 1, max = 10, message = "页大小必须在1-10之间")
                                  @PathVariable("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return RespBean.ok(new PageInfo<>(operatorLogService.selectAllOperatorLog()));
    }

    /**
     * 获取操作日志管理页头
     */
    @Log("获取操作日志管理页头")
    @GetMapping("/header")
    public RespBean findHeaderLog() {
        return RespBean.ok(operatorLogService.selectOperatorHeaderCount());
    }
}
