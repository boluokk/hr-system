package org.boluo.hr.controller.per.mv;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.boluo.hr.pojo.BaseEmployeeRemove;
import org.boluo.hr.pojo.BaseEmployeeTrain;
import org.boluo.hr.pojo.Employee;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.service.DepartmentService;
import org.boluo.hr.service.EmployeeRemoveService;
import org.boluo.hr.service.EmployeeService;
import org.boluo.hr.service.JobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
    private final JobLevelService jobLevelService;
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeRemoveController(DepartmentService departmentService,
                                    EmployeeRemoveService employeeRemoveService,
                                    JobLevelService jobLevelService,
                                    EmployeeService employeeService) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
        this.employeeRemoveService = employeeRemoveService;
        this.jobLevelService = jobLevelService;
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
     * 返回所有职称
     */
    @GetMapping("/joblevels")
    public RespBean findAllJobLevel() {
        return RespBean.ok(jobLevelService.selectAll());
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
    public RespBean modify(BaseEmployeeRemove baseEmployeeRemove) {
        if (employeeRemoveService.update(baseEmployeeRemove)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    /**
     * 新增调岗
     */
    @PutMapping("/add/{workId}")
    @Transactional(rollbackFor = Exception.class)
    public RespBean add(BaseEmployeeRemove baseEmployeeRemove,
                        @PathVariable("workId") String workId) {
        Employee employee = employeeService.selectEmployeeByWorkId(workId);
        if (employee == null) {
            return RespBean.error("员工不存在!");
        }
        baseEmployeeRemove.setEmployeeId(employee.getId());
        // 记录当前部门和职称
        baseEmployeeRemove.setBeforeDepartmentId(employee.getDepartmentId());
        baseEmployeeRemove.setBeforeJobId(employee.getJobLevelId());
        // 修改部门和职称
        employee.setDepartmentId(baseEmployeeRemove.getAfterDepartmentId());
        employee.setJobLevelId(baseEmployeeRemove.getAfterJobId());
        // 更新原来员工
        if (!employeeService.update(employee)) {
            return RespBean.error("部门或职称更新失败!");
        }
        if (employeeRemoveService.insert(baseEmployeeRemove)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }
}
