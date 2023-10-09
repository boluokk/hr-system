package org.boluo.hr.controller.per.salary;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.boluo.hr.pojo.Adjustsalary;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.service.AdjustsalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 🍍
 * @date 2023/10/09
 */

@RestController
@RequestMapping("/per/salary")
public class EmployeeSalaryController {

    private final AdjustsalaryService adjustsalaryService;

    @Autowired
    public EmployeeSalaryController(AdjustsalaryService adjustsalaryService) {
        this.adjustsalaryService = adjustsalaryService;
    }

    @GetMapping("/{pageNum}/{pageSize}")
    public RespBean findPage(@PathVariable("pageNum") Integer pageNum,
                             @PathVariable("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return RespBean.ok(new PageInfo<>(adjustsalaryService.selectAll()));
    }


    @PutMapping("/modify")
    public RespBean modify(Adjustsalary adjustsalary) {
        if (adjustsalaryService.update(adjustsalary)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    @DeleteMapping("/delete/{id}")
    public RespBean remove(@PathVariable("id") Integer id) {
        if (adjustsalaryService.delete(id)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    @PutMapping("/add")
    public RespBean add(Adjustsalary adjustsalary) {
        if (adjustsalaryService.insert(adjustsalary)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }
}
