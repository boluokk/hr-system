package org.boluo.hr.controller.per.mv;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.boluo.hr.pojo.Employeeremove;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.service.DepartmentService;
import org.boluo.hr.service.EmployeeRewardPunishmentService;
import org.boluo.hr.service.EmployeeRemoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 🍍
 * @date 2023/10/09
 */

@RestController
@RequestMapping("/per/mv")
public class EmployeeRemoveController {

    private final DepartmentService departmentService;
    private final EmployeeRemoveService employeeremoveService;
    private final EmployeeRewardPunishmentService employeecService;

    @Autowired
    public EmployeeRemoveController(DepartmentService departmentService,
                                    EmployeeRemoveService employeeremoveService,
                                    EmployeeRewardPunishmentService employeecService) {
        this.departmentService = departmentService;
        this.employeeremoveService = employeeremoveService;
        this.employeecService = employeecService;
    }

    @GetMapping("/{pageNum}/{pageSize}")
    public RespBean findPage(@PathVariable("pageNum") Integer pageNum,
                             @PathVariable("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return RespBean.ok(new PageInfo<>(employeeremoveService.selectAll()));
    }

    @GetMapping("/departments")
    public RespBean findAllDepartment() {
        return RespBean.ok(departmentService.selectAll());
    }

    @DeleteMapping("/delete/{id}")
    public RespBean remove(@PathVariable("id") Integer id) {
        if (employeeremoveService.delete(id)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    @PutMapping("/modify")
    public RespBean modify(Employeeremove employeeremove) {
        if (employeeremoveService.update(employeeremove)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    @PutMapping("/add/{workId}")
    public RespBean add(Employeeremove employeeremove,
                        @PathVariable("workId") String workId) {

        Integer employeeId = employeecService.selectByWorkId(workId);

        if (employeeId == null) {
            return RespBean.error("员工不存在!");
        }

        employeeremove.setEid(employeeId);
        if (employeeremoveService.insert(employeeremove)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }
}
