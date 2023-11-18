package org.boluo.hr.controller.per.mv;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.boluo.hr.annotation.Log;
import org.boluo.hr.pojo.InsertEmployeeRemove;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.pojo.UploadEmployee;
import org.boluo.hr.pojo.UploadEmployeeRemove;
import org.boluo.hr.service.DepartmentService;
import org.boluo.hr.service.EmployeeRemoveService;
import org.boluo.hr.service.EmployeeService;
import org.boluo.hr.service.JobLevelService;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * 调岗信息
 *
 * @author 🍍
 * @date 2023/10/09
 */
@RestController
@RequestMapping("/per/mv")
@Validated
public class EmployeeRemoveController {

    @Resource
    private DepartmentService departmentService;
    @Resource
    private EmployeeRemoveService employeeRemoveService;
    @Resource
    private JobLevelService jobLevelService;
    @Resource
    private EmployeeService employeeService;

    /**
     * 调岗分页
     */
    @GetMapping("/{pageNum}/{pageSize}")
    @Log("查询调岗分页")
    public RespBean findPage(@Min(value = 1, message = "页码不能小于1")
                             @PathVariable("pageNum") Integer pageNum,
                             @Min(value = 1, message = "页大小不能小于1")
                             @Max(value = 10, message = "页大小不能大于10")
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
    public RespBean remove(@Min(value = 1, message = "id不能小于1")
                           @PathVariable("id") Integer id) {
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
    public RespBean modify(@Valid @RequestBody UploadEmployeeRemove uploadEmployeeRemove) {
        if (employeeRemoveService.update(uploadEmployeeRemove)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    /**
     * 新增调岗
     */
    @PutMapping("/add/{workId}")
    @Log("新增调岗")
    public RespBean add(@Valid @RequestBody InsertEmployeeRemove insertEmployeeRemove,
                        @Length(min = 8, max = 8, message = "工号长度必须为8位")
                        @PathVariable("workId") String workId) {
        UploadEmployee employee = employeeService.selectEmployeeByWorkId(workId);
        if (employee == null) {
            return RespBean.error("员工不存在!");
        }
        insertEmployeeRemove.setEmployeeId(employee.getId());
        // 记录当前部门和职称
        insertEmployeeRemove.setBeforeDepartmentId(employee.getDepartmentId());
        insertEmployeeRemove.setBeforeJobId(employee.getJobLevelId());
        // 修改部门和职称
        employee.setDepartmentId(insertEmployeeRemove.getAfterDepartmentId());
        employee.setJobLevelId(insertEmployeeRemove.getAfterJobId());
        // 更新原来员工
        if (!employeeService.update(employee)) {
            return RespBean.error("部门或职称更新失败!");
        }
        if (employeeRemoveService.insert(insertEmployeeRemove)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }
}
