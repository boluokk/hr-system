package org.boluo.hr.controller.per.train;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.boluo.hr.annotation.Log;
import org.boluo.hr.pojo.InsertEmployeeTrain;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.pojo.UploadEmployee;
import org.boluo.hr.pojo.UploadEmployeeTrain;
import org.boluo.hr.service.EmployeeService;
import org.boluo.hr.service.EmployeeTrainService;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * 培训信息
 *
 * @author 🍍
 * @date 2023/10/1
 */
@RequestMapping("/per/train")
@RestController
@Validated
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
    @Log("查询培训分页")
    public RespBean findByPage(@Min(value = 1, message = "页码不能小于1")
                               @PathVariable("pageNum") Integer pageNum,
                               @Range(min = 1, max = 10, message = "页大小不能小于1或大于10")
                               @PathVariable("pageSize")
                               Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return RespBean.ok(new PageInfo<>(employeeTrainService.selectAll()));
    }

    /**
     * 培训修改
     */
    @PutMapping("/modify")
    @Log("培训修改")
    public RespBean modify(@Valid @RequestBody UploadEmployeeTrain uploadEmployeeTrain) {
        if (employeeTrainService.update(uploadEmployeeTrain)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    /**
     * 培训删除
     */
    @DeleteMapping("/delete/{id}")
    @Log("培训删除")
    public RespBean removeOne(@Min(value = 1, message = "id不能小于1")
                              @PathVariable("id") Integer id) {
        System.out.println(id);
        if (employeeTrainService.delete(id)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    /**
     * 新增培训
     */
    @PutMapping("/add/{workId}")
    @Log("新增培训")
    public RespBean addOne(@Length(min = 8, max = 8, message = "员工号长度必须为8位")
                           @PathVariable("workId") String workId,
                           @Valid @RequestBody InsertEmployeeTrain insertEmployeeTrain) {
        UploadEmployee employee = employeeService.selectEmployeeByWorkId(workId);
        if (employee == null) {
            return RespBean.error("员工号不存在");
        }
        insertEmployeeTrain.setEmployeeId(employee.getId());
        if (employeeTrainService.insert(insertEmployeeTrain)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }
}
