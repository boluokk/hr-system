package org.boluo.hr.controller.per.emp;

import org.boluo.hr.annotation.Log;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.pojo.UploadEmployee;
import org.boluo.hr.service.EmpInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

/**
 * 员工信息
 *
 * @author 🍍
 * @date 2023/10/1
 */
@RestController
@RequestMapping("/per/emp")
@Validated
public class EmpInfoController {

    private final EmpInfoService empInfoService;

    @Autowired
    public EmpInfoController(EmpInfoService empInfoService) {
        this.empInfoService = empInfoService;
    }

    /**
     * 通过名字查询员工
     */
    @GetMapping("/{empName}")
    @Log("通过名字查询员工")
    public RespBean findAll(@PathVariable("empName") String empName) {
        return RespBean.ok(empInfoService.selectByEmpName(empName));
    }

    /**
     * 删除员工
     */
    @DeleteMapping("/delete/{id}")
    @Log("删除员工")
    public RespBean removeOne(@Min(value = 1, message = "id必须大于0")
                              @PathVariable("id") Integer id) {
        if (empInfoService.delete(id)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    /**
     * 修改员工
     */
    @PutMapping("/modify/{id}/{name}")
    @Log("修改员工")
    public RespBean modifyOne(@Min(value = 1, message = "id必须大于0")
                              @PathVariable("id") Integer id,
                              @PathVariable("name") String name) {
        UploadEmployee uploadEmployee = new UploadEmployee().setId(id).setName(name);
        if (empInfoService.update(uploadEmployee)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }
}
