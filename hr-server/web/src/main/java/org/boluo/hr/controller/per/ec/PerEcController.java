package org.boluo.hr.controller.per.ec;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.boluo.hr.pojo.Employeeec;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.service.EmployeeRewardPunishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 🍍
 * @date 2023/10/1
 */
@RestController
@RequestMapping("/per/ec")
public class PerEcController {

    private final EmployeeRewardPunishmentService employeecService;

    @Autowired
    public PerEcController(EmployeeRewardPunishmentService employeecService) {
        this.employeecService = employeecService;
    }

    @GetMapping("/{pageNum}/{pageSize}")
    public RespBean findByPage(@PathVariable("pageNum") Integer pageNum,
                               @PathVariable("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return RespBean.ok(new PageInfo<>(employeecService.selectAll()));
    }

    @PutMapping("/modify")
    public RespBean modifyOne(Employeeec employeeec) {
        if (employeecService.update(employeeec)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    @DeleteMapping("/delete/{id}")
    public RespBean removeOne(@PathVariable("id") Integer id) {
        if (employeecService.delete(id)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    @PutMapping("/add")
    public RespBean addOne(Employeeec employeeec) {
        Integer employeeId = employeecService.selectByWorkId(employeeec.getWorkId());
        if (employeeId == null) {
            return RespBean.error("员工号不存在");
        }
        employeeec.setEid(employeeId);
        if (employeecService.insert(employeeec)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }
}
