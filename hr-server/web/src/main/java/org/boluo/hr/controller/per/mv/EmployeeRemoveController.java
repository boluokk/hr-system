package org.boluo.hr.controller.per.mv;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.boluo.hr.pojo.EmployeeRemove;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.service.DepartmentService;
import org.boluo.hr.service.EmployeeRemoveService;
import org.boluo.hr.service.EmployeeRewardPunishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 调岗信息
 *
 * @author 🍍
 * @date 2023/10/09
 */
@RestController
@RequestMapping("/per/mv")
public class EmployeeRemoveController {

    private final DepartmentService departmentService;
    private final EmployeeRemoveService employeeRemoveService;
    private final EmployeeRewardPunishmentService employeeRewardPunishmentService;

    @Autowired
    public EmployeeRemoveController(DepartmentService departmentService,
                                    EmployeeRemoveService employeeRemoveService,
                                    EmployeeRewardPunishmentService employeeRewardPunishmentService) {
        this.departmentService = departmentService;
        this.employeeRemoveService = employeeRemoveService;
        this.employeeRewardPunishmentService = employeeRewardPunishmentService;
    }

    /**
     * 调岗分页
     */
    @GetMapping("/{pageNum}/{pageSize}")
    public RespBean findPage(@PathVariable("pageNum") Integer pageNum,
                             @PathVariable("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return RespBean.ok(new PageInfo<>(employeeRemoveService.selectAll()));
    }

    /**
     * 返回所有部门
     */
    @GetMapping("/departments")
    public RespBean findAllDepartment() {
        return RespBean.ok(departmentService.selectAll());
    }

    /**
     * 删除员工
     */
    @DeleteMapping("/delete/{id}")
    public RespBean remove(@PathVariable("id") Integer id) {
        if (employeeRemoveService.delete(id)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    /**
     * 修改调岗
     */
    @PutMapping("/modify")
    public RespBean modify(EmployeeRemove employeeRemove) {
        if (employeeRemoveService.update(employeeRemove)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    /**
     * 新增调岗
     */
    @PutMapping("/add/{workId}")
    public RespBean add(EmployeeRemove employeeRemove,
                        @PathVariable("workId") String workId) {

        Integer employeeId = employeeRewardPunishmentService.selectByWorkId(workId);

        if (employeeId == null) {
            return RespBean.error("员工不存在!");
        }

        employeeRemove.setEmployeeId(employeeId);
        if (employeeRemoveService.insert(employeeRemove)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }
}
