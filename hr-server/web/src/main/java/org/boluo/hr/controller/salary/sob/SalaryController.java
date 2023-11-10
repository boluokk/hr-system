package org.boluo.hr.controller.salary.sob;

import org.boluo.hr.annotation.Log;
import org.boluo.hr.pojo.InsertSalary;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.pojo.UploadSalary;
import org.boluo.hr.service.SalaryService;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 工资账套信息
 *
 * @author 🍍
 * @date 2023/10/1
 */
@RestController
@RequestMapping("/sal/sob")
@Validated
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
    public RespBean add(@Valid @RequestBody InsertSalary insertSalary) {
        if (salaryService.insert(insertSalary)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    /**
     * 删除工资账套
     */
    @DeleteMapping("/delete/{id}")
    @Log("删除工资账套")
    public RespBean remove(@Min(value = 1, message = "id 必须大于 0")
                           @PathVariable("id") Integer id) {
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
    public RespBean modify(@Valid @RequestBody UploadSalary uploadSalary) {
        if (salaryService.update(uploadSalary)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    /**
     * 批量工资账套删除
     */
    @DeleteMapping("/delete/many/")
    @Log("批量工资账套删除")
    public RespBean removeMany(@NotNull(message = "id 不能为空")
                               @Size(min = 1, message = "id 长度必须大于 0")
                               Integer[] ids) {
        if (salaryService.deleteMany(ids)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }
}
