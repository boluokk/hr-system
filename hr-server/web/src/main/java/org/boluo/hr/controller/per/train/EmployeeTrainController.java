package org.boluo.hr.controller.per.train;

import org.boluo.hr.pojo.Employeetrain;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.service.EmployeetrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 🍍
 * @date 2023/10/1
 */
@RequestMapping("/per/train")
@RestController
public class EmployeeTrainController {

    private final EmployeetrainService employeetrainService;

    @Autowired
    public EmployeeTrainController(EmployeetrainService employeetrainService) {
        this.employeetrainService = employeetrainService;
    }

    @GetMapping("/")
    public RespBean findAll() {
        return RespBean.ok(employeetrainService.selectAllWithEmpName());
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

    @PutMapping("/")
    public RespBean addOne(Employeetrain employeetrain) {
        if (employeetrainService.insertOne(employeetrain)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }
}
