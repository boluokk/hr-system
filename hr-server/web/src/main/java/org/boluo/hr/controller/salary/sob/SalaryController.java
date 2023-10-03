package com.boluo.hr.controller.salary.sob;

import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.pojo.Salary;
import com.boluo.hr.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author @1352955539(boluo)
 * @date 2021/2/21 - 11:32
 */
@RestController
@RequestMapping("/sal/sob")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @GetMapping("/")
    public RespBean showSalary() {
        List<Salary> allSalary = salaryService.getAllSalary();
        return RespBean.ok(allSalary);
    }

    @PutMapping("/")
    public RespBean addSalary(Salary salary) {
        int i = salaryService.addOfOne(salary);
        if(i==1) {
            return RespBean.ok("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteSalary(@PathVariable("id") Integer id) {
        int i = salaryService.delOfOne(id);
        if(i==1){
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    @PutMapping("/change/one")
    public RespBean editSalary(Salary salary) {
        int i = salaryService.editSalary(salary);
        if(i==1) {
            return RespBean.ok("修改成功！");
        }
        return RespBean.error("修改失败！");
    }

    @DeleteMapping("/delete/many/")
    public RespBean deleteSalaryMany(Integer[] ids) {
        int i = salaryService.deleteOfMany(ids);
        if(i==ids.length) {
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败");
    }
}
