package org.boluo.hr.controller.per.train;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.boluo.hr.pojo.EmployeeTrain;
import org.boluo.hr.pojo.Employee;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.service.EmployeeService;
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

    private final EmployeeService employeeService;
    private final EmployeeTrainService employeeTrainService;

    @Autowired
    public EmployeeTrainController(EmployeeService employeeService,
                                   EmployeeTrainService employeeTrainService) {
        this.employeeService = employeeService;
        this.employeeTrainService = employeeTrainService;
    }

    /**
     * 培训分页
     */
    @GetMapping("/{pageNum}/{pageSize}")
    public RespBean findByPage(@PathVariable("pageNum") Integer pageNum,
                               @PathVariable("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return RespBean.ok(new PageInfo<>(employeeTrainService.selectAll()));
    }

    /**
     * 培训修改
     */
    @PutMapping("/modify")
    public RespBean modify(@RequestBody EmployeeTrain employeeTrain) {
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
    public RespBean addOne(@PathVariable("workId") String workId,
                           @RequestBody EmployeeTrain employeeTrain) {
        Employee employee = employeeService.selectEmployeeByWorkId(workId);
        if (employee == null) {
            return RespBean.error("员工号不存在");
        }
        employeeTrain.setEmployeeId(employee.getId());
        if (employeeTrainService.insert(employeeTrain)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }
}
