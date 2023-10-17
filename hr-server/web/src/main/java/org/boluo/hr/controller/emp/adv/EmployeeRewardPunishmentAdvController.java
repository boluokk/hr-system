package org.boluo.hr.controller.emp.adv;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.service.EmployeeRewardPunishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 奖惩信息
 *
 * @author 🍍
 * @date 2023/10/1
 */

@RestController
@RequestMapping("/emp/adv/c")
public class EmployeeRewardPunishmentAdvController {

    private final EmployeeRewardPunishmentService employeeRewardPunishmentService;

    @Autowired
    public EmployeeRewardPunishmentAdvController(EmployeeRewardPunishmentService employeeRewardPunishmentService) {
        this.employeeRewardPunishmentService = employeeRewardPunishmentService;
    }

    /**
     * 奖惩分页
     */
    @GetMapping("/{pageNum}/{pageSize}")
    public RespBean findByPage(@PathVariable("pageNum") Integer pageNum,
                               @PathVariable("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return RespBean.ok(new PageInfo<>(employeeRewardPunishmentService.selectAll()));
    }
}