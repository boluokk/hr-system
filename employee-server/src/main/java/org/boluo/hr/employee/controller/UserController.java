package org.boluo.hr.employee.controller;

import org.bluo.global.pojo.RespBean;
import org.bluo.hr.service.api.HrServiceClient;
import org.bluo.hr.service.api.pojo.SalaryTableSearch;
import org.boluo.hr.employee.mapper.UserMapper;
import org.boluo.hr.employee.pojo.EmployeeSign;
import org.boluo.hr.employee.pojo.Leave;
import org.boluo.hr.employee.service.impl.LeaveServiceImpl;
import org.boluo.hr.employee.service.impl.SignServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author boluo
 * @date 2024/02/09
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private SignServiceImpl signServiceImpl;

    @Resource
    private LeaveServiceImpl leaveServiceImpl;

    @Resource
    private HrServiceClient hrServiceClient;

    @Resource
    private UserMapper userMapper;

    @GetMapping("/sign")
    public RespBean findSign(@RequestParam("employeeId") int employeeId) {
        EmployeeSign sign = signServiceImpl.getOne(employeeId);
        return RespBean.ok(sign);
    }

    @PostMapping("/sign/{employeeId}")
    public RespBean sign(@PathVariable("employeeId") int employeeId) {
        if (signServiceImpl.sign(employeeId)) {
            return RespBean.ok("签到成功");
        }
        return RespBean.error("签到失败");
    }

    @PostMapping("/sign/out/{employeeId}")
    public RespBean signOut(@PathVariable("employeeId") int employeeId) {
        if (signServiceImpl.signOut(employeeId)) {
            return RespBean.ok("签退成功");
        }
        return RespBean.error("签退失败");
    }

    @GetMapping("/sign/month/count")
    public RespBean getCount(@RequestParam("employeeId") int employeeId) {
        return RespBean.ok(signServiceImpl.getCurrentMonthCount(employeeId));
    }

    @PostMapping("/leave/add")
    public RespBean addLeave(@RequestBody Leave leave) {
        if (leaveServiceImpl.add(leave)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    @GetMapping("/leave")
    public RespBean getAll(@RequestParam("employeeId") int employeeId) {
        return RespBean.ok(leaveServiceImpl.findAll(employeeId));
    }

    @DeleteMapping("/leave/delete/{id}")
    public RespBean delete(@PathVariable("id") int id) {
        if (leaveServiceImpl.delete(id)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    @GetMapping("/sal/table/{employeeId}")
    public RespBean findPage(@PathVariable("employeeId") int employeeId) {
        SalaryTableSearch salaryTableSearch = new SalaryTableSearch();
        String workId = userMapper.findWorkId(employeeId);
        salaryTableSearch.setWorkId(workId);
        salaryTableSearch.setDate(new Date());
        return RespBean.ok(hrServiceClient.findPage(0, 10, salaryTableSearch));
    }
}
