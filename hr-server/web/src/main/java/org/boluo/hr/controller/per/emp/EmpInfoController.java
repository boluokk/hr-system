package org.boluo.hr.controller.per.emp;

import org.boluo.hr.pojo.Employee;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.service.EmpInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 员工信息
 *
 * @author 🍍
 * @date 2023/10/1
 */
@RestController
@RequestMapping("/per/emp")
public class EmpInfoController {

    private final EmpInfoService empInfoService;

    @Autowired
    public EmpInfoController(EmpInfoService empInfoService) {
        this.empInfoService = empInfoService;
    }

    /**
     * 通过名字查询员工
     */
    @GetMapping("/byEmpName/{empName}")
    public RespBean findAll(@PathVariable("empName") String empName) {
        return RespBean.ok(empInfoService.selectByEmpName(empName));
    }

    /**
     * 删除员工
     */
    @DeleteMapping("/delete/{id}")
    public RespBean removeOne(@PathVariable("id") Integer id) {
        if (empInfoService.delete(id)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    /**
     * 修改员工
     */
    @PutMapping("/modify")
    public RespBean modifyOne(Employee employee) {
        if (empInfoService.update(employee)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }
}
