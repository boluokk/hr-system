package com.boluo.hr.controller.personnel.emp;

import com.boluo.hr.pojo.*;
import com.boluo.hr.service.*;
import com.boluo.hr.service.util.ExportAndImortExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author @1352955539(boluo)
 * @date 2021/2/10 - 23:00
 */
@RestController
@RequestMapping("/personnel/emp")
public class EmployeeController {

    @Autowired
    EmployeeService empLoyeeService;
    @Autowired
    NationService nationService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    PoliticsstatusService politicsstatusService;
    @Autowired
    JobLevelService jobLevelService;
    @Autowired
    PositionService positionService;

    private final static String pattern = "^[0-9]{8}";

    @GetMapping("/")
    public RespBean showEmp(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        RespEmpBean reb = new RespEmpBean();
        if (pageNum != null && pageSize != null) {
            int ResultPageNum = pageSize * (pageNum - 1);
            reb.setEmployees(empLoyeeService.selectAllEmp(ResultPageNum, pageSize));
            reb.setTotal(empLoyeeService.selectTotal());
        }
        return RespBean.ok(reb);
    }

    @GetMapping("/{empName}")
    public RespBean showEmpBySeach(@PathVariable("empName") String empName) {
        List<Employee> employees = empLoyeeService.selectByEmpName(empName);
        return RespBean.ok(employees);
    }

    @GetMapping("/nation")
    public RespBean resNation() {
        return RespBean.ok(empLoyeeService.testNation());
    }

    @PutMapping("/one")
    public RespBean addUser(Employee employee) {
        boolean matches = Pattern.matches(pattern, employee.getWorkid());
        if (!matches) return RespBean.error("workId不能为数字!!!");
        if (empLoyeeService.insertEmpOfOne(employee) == 1) {
            return RespBean.ok("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @PutMapping("/change")
    public RespBean changeEmp(Employee employee) {
        boolean matches = Pattern.matches(pattern, employee.getWorkid());
        if (!matches) return RespBean.error("workId不能为数字!!!");
        if (empLoyeeService.upDateEmp(employee) == 1) {
            return RespBean.ok("修改成功！");
        }
        return RespBean.error("修改失败！");
    }

    @GetMapping("/max/workid")
    public RespBean getMaxWorkID() {
        Integer i = empLoyeeService.selectMaxWorkID();
        Object format = String.format("%08d", i);
        return RespBean.ok(format);
    }

    @DeleteMapping("/{id}")
    public RespBean deleteOne(@PathVariable Integer id) {
        if (empLoyeeService.deltebyOne(id) == 1) {
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败！");
    }


    @GetMapping("/export")
    public ResponseEntity<byte[]> export() {
        List<Employee> list = empLoyeeService.selectAllByNothing();
        return ExportAndImortExcelUtil.exportData(list);
    }

    @PostMapping("/import")
    public RespBean importData(MultipartFile file) throws IOException {
        List<Employee> employees = ExportAndImortExcelUtil.importData(file, nationService.getAllNation(), departmentService.getAllDepInfo(), politicsstatusService.getAllPolitic(), jobLevelService.getALLJobLevel(), positionService.getAllPostion());
        if (empLoyeeService.insertMany(employees) == employees.size()) {
            return RespBean.ok("上传成功！");
        }
        return RespBean.error("上传失败！");
    }


    @PostMapping("/top/search/{pageNum}/{pageSize}")
    public RespEmpBean topSerach(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize, Employee employee) {
        RespEmpBean reb = new RespEmpBean();
        System.out.println(employee);
        List<Employee> list = empLoyeeService.TipSerchByEmp(pageNum - 1, pageSize, employee);
        int i = empLoyeeService.TipCount(employee);
        reb.setEmployees(list);
        reb.setTotal(i);
        return reb;
    }
}
