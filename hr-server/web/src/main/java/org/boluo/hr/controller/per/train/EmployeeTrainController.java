package org.boluo.hr.controller.per.train;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.boluo.hr.pojo.EmployeeTrain;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.service.EmployeeRewardPunishmentService;
import org.boluo.hr.service.EmployeeTrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 培训信息
 *
 * @author 🍍
 * @date 2023/10/1
 */
@RequestMapping("/per/train")
@RestController
public class EmployeeTrainController {

    private final EmployeeRewardPunishmentService employeeRewardPunishmentService;
    private final EmployeeTrainService employeeTrainService;

    @Autowired
    public EmployeeTrainController(EmployeeRewardPunishmentService employeeRewardPunishmentService,
                                   EmployeeTrainService employeeTrainService) {
        this.employeeRewardPunishmentService = employeeRewardPunishmentService;
        this.employeeTrainService = employeeTrainService;
    }

    /**
     * 培训分页
     */
    @GetMapping("/{pageNum}/{pageSize}")
    public RespBean findByPage(@PathVariable("pageNum") Integer pageNum,
                               @PathVariable("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return RespBean.ok(new PageInfo<>(employeeTrainService.selectAllWithEmployeeName()));
    }

    /**
     * 培训修改
     */
    @PutMapping("/modify")
    public RespBean modify(EmployeeTrain employeeTrain) {
        if (employeeTrainService.update(employeeTrain)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    /**
     * 培训删除
     */
    @DeleteMapping("/delete/{id}")
    public RespBean removeOne(@PathVariable("id") Integer id) {
        if (employeeTrainService.delete(id)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    /**
     * 培训新增
     */
    @PutMapping("/add/{workId}")
    public RespBean addOne(@PathVariable("workId") String workId, EmployeeTrain employeeTrain) {
        Integer employeeId = employeeRewardPunishmentService.selectByWorkId(workId);
        if (employeeId == null) {
            return RespBean.error("员工号不存在");
        }
        employeeTrain.setEmployeeId(employeeId);
        if (employeeTrainService.insert(employeeTrain)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }
}
