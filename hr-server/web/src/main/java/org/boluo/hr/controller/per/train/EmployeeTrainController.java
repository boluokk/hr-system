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
 * @author 🍍
 * @date 2023/10/1
 */
@RequestMapping("/per/train")
@RestController
public class EmployeeTrainController {

    private final EmployeeRewardPunishmentService employeecService;
    private final EmployeeTrainService employeeTrainService;

    @Autowired
    public EmployeeTrainController(EmployeeRewardPunishmentService employeecService,
                                   EmployeeTrainService employeeTrainService) {
        this.employeecService = employeecService;
        this.employeeTrainService = employeeTrainService;
    }

    @GetMapping("/{pageNum}/{pageSize}")
    public RespBean findByPage(@PathVariable("pageNum") Integer pageNum,
                               @PathVariable("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return RespBean.ok(new PageInfo<>(employeeTrainService.selectAllWithEmployeeName()));
    }

    @PutMapping("/modify")
    public RespBean modify(EmployeeTrain employeeTrain) {
        if (employeeTrainService.update(employeeTrain)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    @DeleteMapping("/delete/{id}")
    public RespBean removeOne(@PathVariable("id") Integer id) {
        if (employeeTrainService.delete(id)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    @PutMapping("/add/{workId}")
    public RespBean addOne(@PathVariable("workId") String workId, EmployeeTrain employeeTrain) {
        Integer employeeId = employeecService.selectByWorkId(workId);
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
