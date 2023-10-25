package org.boluo.hr.controller.per.mv;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.boluo.hr.annotation.Log;
import org.boluo.hr.pojo.EmployeeRemove;
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
    @Log("查询调岗分页")
    public RespBean findPage(@PathVariable("pageNum") Integer pageNum,
                             @PathVariable("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return RespBean.ok(new PageInfo<>(employeeRemoveService.selectAll()));
    }

    /**
     * 返回所有职称
     */
    @GetMapping("/joblevels")
    @Log("查询所有职称")
    public RespBean findAllJobLevel() {
        return RespBean.ok(jobLevelService.selectAll());
    }


    /**
     * 返回所有部门
     */
    @GetMapping("/departments")
    @Log("查询所有部门")
    public RespBean findAllDepartment() {
        return RespBean.ok(departmentService.selectAll());
    }

    /**
     * 删除调岗
     */
    @DeleteMapping("/delete/{id}")
    @Log("删除调岗")
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
    @Log("修改调岗")
    public RespBean modify(@RequestBody EmployeeRemove employeeRemove) {
        if (employeeRemoveService.update(employeeRemove)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    /**
     * 新增调岗
     */
    @PutMapping("/add/{workId}")
    @Log("新增调岗")
    @Transactional(rollbackFor = Exception.class)
    public RespBean add(@RequestBody EmployeeRemove employeeRemove,
                        @PathVariable("workId") String workId) {
        Employee employee = employeeService.selectEmployeeByWorkId(workId);
        if (employee == null) {
            return RespBean.error("员工不存在!");
        }
        employeeRemove.setEmployeeId(employee.getId());
        // 记录当前部门和职称
        employeeRemove.setBeforeDepartmentId(employee.getDepartmentId());
        employeeRemove.setBeforeJobId(employee.getJobLevelId());
        // 修改部门和职称
        employee.setDepartmentId(employeeRemove.getAfterDepartmentId());
        employee.setJobLevelId(employeeRemove.getAfterJobId());
        // 更新原来员工
        if (!employeeService.update(employee)) {
            return RespBean.error("部门或职称更新失败!");
        }
        if (employeeRemoveService.insert(employeeRemove)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }
}
