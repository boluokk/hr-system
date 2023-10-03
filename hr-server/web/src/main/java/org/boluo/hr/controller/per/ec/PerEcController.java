package com.boluo.hr.controller.per.ec;

import org.boluo.hr.pojo.Employeeec;
import org.boluo.hr.pojo.RespBean;
import com.boluo.hr.service.EmployeecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author @1352955539(boluo)
 * @date 2021/3/28 - 22:03
 */
@RestController
@RequestMapping("/per/ec")
public class PerEcController {

    private final EmployeecService employeecService;

    @Autowired
    public PerEcController(EmployeecService employeecService) {
        this.employeecService = employeecService;
    }

    @GetMapping("/")
    public RespBean list() {
        return RespBean.ok(employeecService.getAll());
    }

    @PutMapping("/change")
    public RespBean modify(Employeeec employeeec) {
        int update = employeecService.update(employeeec);
        if (update == 1) {
            return RespBean.ok("修改成功！");
        } else {
            return RespBean.error("修改失败！");
        }
    }

    @DeleteMapping("/del/{id}")
    public RespBean deleteEmpc(@PathVariable("id") Integer id) {
        int delete = employeecService.delete(id);
        if (delete == 1) {
            return RespBean.ok("删除成功！");
        } else {
            return RespBean.error("删除失败！");
        }
    }

    @PutMapping("/add/one")
    public RespBean addOne(Employeeec employeeec) {
        int i = employeecService.addEmpc(employeeec);
        if (i == 1) {
            return RespBean.ok("添加成功！");
        } else {
            return RespBean.error("添加失败！");
        }
    }
}
