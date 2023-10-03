package com.boluo.hr.controller.salary.sobcfg;

import org.boluo.hr.pojo.Employee;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.pojo.RespEmpBean;
import com.boluo.hr.service.SobCfgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author @1352955539(boluo)
 * @date 2021/2/21 - 21:53
 */
@RestController
@RequestMapping("/sal/sobcfg")
public class SobCfgController {

    @Autowired
    SobCfgService sobCfgService;

    @GetMapping("/")
    public RespEmpBean showEmpWithSal(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {
        RespEmpBean rb = new RespEmpBean();
        List<Employee> list = sobCfgService.getEmpWithSalary(pageNum,pageSize);
        int total = sobCfgService.CountEmpWithSal();
        rb.setTotal(total);
        rb.setEmployees(list);
        return rb;
    }

    @GetMapping("/{empid}/{salid}")
    public RespBean addSalWithEmp(@PathVariable("empid") Integer eid,@PathVariable("salid") Integer salid) {
        try {
            sobCfgService.addSalWEmp(eid,salid);
            return RespBean.ok("修改成功!");
        } catch (Exception e) {
            return RespBean.error("修改失败！");
        }
    }
}
