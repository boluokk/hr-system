package com.boluo.hr.controller.per.emp;

import org.boluo.hr.pojo.Employee;
import org.boluo.hr.pojo.RespBean;
import com.boluo.hr.service.EmpInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author @1352955539(boluo)
 * @date 2021/3/28 - 13:30
 */

@RestController
@RequestMapping("/per/emp")
public class EmpInfoController {

    @Autowired
    private EmpInfoService empInfoService;

    @GetMapping("/{empName}")
    public RespBean show(@PathVariable("empName") String empName) {
        return RespBean.ok(empInfoService.selectEmp(empName));
    }

    @DeleteMapping("/{id}")
    public RespBean delEmp(@PathVariable("id") Integer id) {
        int i = empInfoService.deleteEmp(id);
        if(i==1) {
            return RespBean.ok("删除成功！");
        } else {
            return RespBean.error("删除失败！");
        }
    }

    @PutMapping("/one")
    public RespBean changeEmp(Employee employee) {
        int i = empInfoService.updateEmp(employee);
        if(i==1) {
            return RespBean.ok("修改成功！");
        } else {
            return RespBean.error("修改失败！");
        }
    }
}
