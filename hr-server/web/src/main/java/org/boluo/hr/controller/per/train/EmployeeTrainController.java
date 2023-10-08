package org.boluo.hr.controller.per.train;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.boluo.hr.mapper.EmployeeecMapper;
import org.boluo.hr.pojo.Employeetrain;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.service.EmployeecService;
import org.boluo.hr.service.EmployeetrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author 🍍
 * @date 2023/10/1
 */
@RequestMapping("/per/train")
@RestController
public class EmployeeTrainController {

    private final EmployeecService employeecService;
    private final EmployeetrainService employeetrainService;

    @Autowired
    public EmployeeTrainController(EmployeecService employeecService, EmployeetrainService employeetrainService) {
        this.employeecService = employeecService;
        this.employeetrainService = employeetrainService;
    }

    @GetMapping("/{pageNum}/{pageSize}")
    public RespBean findByPage(@PathVariable("pageNum") Integer pageNum,
                               @PathVariable("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return RespBean.ok(new PageInfo<>(employeetrainService.selectAllWithEmpName()));
    }

    @PutMapping("/change/one")
    public RespBean modify(Employeetrain employeetrain) {
        if (employeetrainService.updateOne(employeetrain)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    @DeleteMapping("/{id}")
    public RespBean removeOne(@PathVariable("id") Integer id) {
        if (employeetrainService.deleteEmpTrain(id)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    @PutMapping("/add/{workId}")
    public RespBean addOne(@PathVariable("workId") String workId, Employeetrain employeetrain) {
        Integer employeeId = employeecService.selectByWorkId(workId);
        if (employeeId == null) {
            return RespBean.error("员工号不存在");
        }
        employeetrain.setEid(employeeId);
        if (employeetrainService.insertOne(employeetrain)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }
}
