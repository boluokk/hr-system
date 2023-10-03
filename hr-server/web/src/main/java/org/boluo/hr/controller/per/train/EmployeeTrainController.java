package com.boluo.hr.controller.per.train;

import org.boluo.hr.pojo.Employeetrain;
import org.boluo.hr.pojo.RespBean;
import com.boluo.hr.service.EmployeetrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author @1352955539(boluo)
 * @date 2021/4/16 - 15:04
 */

@RequestMapping("/per/train")
@RestController
public class EmployeeTrainController {

    @Autowired
    EmployeetrainService employeetrainService;

    @GetMapping("/")
    public RespBean show() {
        return RespBean.ok(employeetrainService.getAllWithEmpName());
    }

    @PutMapping("/change/one")
    public RespBean change(Employeetrain employeetrain) {
        Integer i = employeetrainService.updateEmpt(employeetrain);
        if(i==1) {
            return RespBean.ok("修改成功！");
        } else {
            return RespBean.error("修改失败！");
        }
    }

    @DeleteMapping("/{id}")
    public RespBean deleteEmp(@PathVariable("id") Integer id) {
        Integer i = employeetrainService.deleteEmpTrain(id);
        if(i==1) {
            return RespBean.ok("删除成功！");
        } else {
            return RespBean.error("删除失败！");
        }
    }

    @PutMapping("/")
    public RespBean add(Employeetrain employeetrain) {
        Integer i = employeetrainService.addEmpTrain(employeetrain);
        if(i==1) {
            return RespBean.ok("添加成功！");
        } else {
            return RespBean.error("添加失败！");
        }
    }
}
