package org.boluo.hr.controller.salary.sob;

import org.boluo.hr.annotation.Log;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.pojo.Salary;
import org.boluo.hr.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 工资账套信息
 *
 * @author 🍍
 * @date 2023/10/1
 */
@RestController
@RequestMapping("/sal/sob")
public class SalaryController {

    private final SalaryService salaryService;

    @Autowired
    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    /**
     * 所有工资账套
     */
    @GetMapping("/")
    @Log("查询所有工资账套")
    public RespBean findAllSalary() {
        return RespBean.ok(salaryService.selectAllSalary());
    }

    /**
     * 新增工资账套
     */
    @PutMapping("/add")
    @Log("新增工资账套")
    public RespBean add(@RequestBody Salary salary) {
        if (salaryService.insert(salary)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    /**
     * 删除工资账套
     */
    @DeleteMapping("/delete/{id}")
    @Log("删除工资账套")
    public RespBean remove(@PathVariable("id") Integer id) {
        if (salaryService.delete(id)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    /**
     * 修改工资账套
     */
    @PutMapping("/modify")
    @Log("修改工资账套")
    public RespBean modify(@RequestBody Salary salary) {
        if (salaryService.update(salary)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    /**
     * 批量工资账套删除
     */
    @DeleteMapping("/delete/many/")
    @Log("批量工资账套删除")
    public RespBean removeMany(Integer[] ids) {
        if (salaryService.deleteMany(ids)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }
}
