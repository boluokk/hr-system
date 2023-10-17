package org.boluo.hr.controller.per.ec;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.boluo.hr.pojo.EmployeeRewardPunishment;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.service.EmployeeRewardPunishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 奖惩信息
 *
 * @author 🍍
 * @date 2023/10/1
 */
@RestController
@RequestMapping("/per/ec")
public class PerEcController {

    private final EmployeeRewardPunishmentService employeeRewardPunishmentService;

    @Autowired
    public PerEcController(EmployeeRewardPunishmentService employeeRewardPunishmentService) {
        this.employeeRewardPunishmentService = employeeRewardPunishmentService;
    }

    /**
     * 奖惩信息分页
     */
    @GetMapping("/{pageNum}/{pageSize}")
    public RespBean findByPage(@PathVariable("pageNum") Integer pageNum,
                               @PathVariable("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return RespBean.ok(new PageInfo<>(employeeRewardPunishmentService.selectAll()));
    }

    /**
     * 修改奖惩信息
     */
    @PutMapping("/modify")
    public RespBean modify(EmployeeRewardPunishment employeeRewardPunishment) {
        if (employeeRewardPunishmentService.update(employeeRewardPunishment)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    /**
     * 删除奖惩信息
     */
    @DeleteMapping("/delete/{id}")
    public RespBean removeOne(@PathVariable("id") Integer id) {
        if (employeeRewardPunishmentService.delete(id)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    /**
     * 新增奖惩信息
     */
    @PutMapping("/add/{workId}")
    public RespBean addOne(EmployeeRewardPunishment employeeRewardPunishment,
                           @PathVariable("workId") String workId) {
        Integer employeeId = employeeRewardPunishmentService.selectByWorkId(workId);
        if (employeeId == null) {
            return RespBean.error("员工号不存在");
        }
        employeeRewardPunishment.setEmployeeId(employeeId);
        if (employeeRewardPunishmentService.insert(employeeRewardPunishment)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }
}
