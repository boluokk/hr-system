package org.boluo.hr.controller.personnel.emp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.boluo.hr.pojo.Employee;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.pojo.RespEmpBean;
import org.boluo.hr.service.*;
import org.boluo.hr.service.util.ExportAndImortExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author 🍍
 * @date 2023/10/1
 */
@RestController
@RequestMapping("/personnel/emp")
public class EmployeeController {

    private final EmployeeService empLoyeeService;
    private final NationService nationService;
    private final DepartmentService departmentService;
    private final PoliticsstatusService politicsstatusService;
    private final JobLevelService jobLevelService;
    private final PositionService positionService;

    @Autowired
    public EmployeeController(EmployeeService empLoyeeService, NationService nationService,
                              DepartmentService departmentService, PoliticsstatusService politicsstatusService,
                              JobLevelService jobLevelService, PositionService positionService) {
        this.empLoyeeService = empLoyeeService;
        this.nationService = nationService;
        this.departmentService = departmentService;
        this.politicsstatusService = politicsstatusService;
        this.jobLevelService = jobLevelService;
        this.positionService = positionService;
    }

    private final static String PATTERN = "^[0-9]{8}";

    @GetMapping("/{pageNum}/{pageSize}")
    public RespBean findPages(@PathVariable("pageNum") Integer pageNum,
                              @PathVariable("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Employee> employees = empLoyeeService.selectAll();
        return RespBean.ok(new PageInfo<>(employees));
    }

    @GetMapping("/{empName}")
    public RespBean findEmpByEmpName(@PathVariable("empName") String empName) {
        return RespBean.ok(empLoyeeService.selectByEmpName(empName));
    }

    @GetMapping("/nation")
    public RespBean findAllNation() {
        return RespBean.ok(empLoyeeService.selectNations());
    }

    @PutMapping("/one")
    public RespBean add(Employee employee) {
        boolean matches = Pattern.matches(PATTERN, employee.getWorkid());
        if (!matches) {
            return RespBean.error("workId不能为数字!!!");
        }
        if (empLoyeeService.insertOne(employee)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    @PutMapping("/change")
    public RespBean modify(Employee employee) {
        boolean matches = Pattern.matches(PATTERN, employee.getWorkid());
        if (!matches) {
            return RespBean.error("workId不能为数字!!!");
        }
        if (empLoyeeService.update(employee)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    @GetMapping("/max/workid")
    public RespBean findMaxWorkId() {
        Object format = String.format("%08d", empLoyeeService.selectMaxWorkId());
        return RespBean.ok(format);
    }

    @DeleteMapping("/{id}")
    public RespBean deleteOne(@PathVariable Integer id) {
        if (empLoyeeService.delete(id)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }


    @GetMapping("/export")
    public ResponseEntity<byte[]> export() {
        List<Employee> list = empLoyeeService.selectAll();
        return ExportAndImortExcelUtil.exportData(list);
    }

    @PostMapping("/import")
    public RespBean importEmployees(MultipartFile file) throws IOException {
        List<Employee> employees =
                ExportAndImortExcelUtil.importData(file, nationService.selectAllNation(),
                        departmentService.selectAll(), politicsstatusService.selectAllPolitic(),
                        jobLevelService.selectAll(), positionService.selectAllPostion());
        if (empLoyeeService.insertMany(employees) == employees.size()) {
            return RespBean.ok("上传成功！");
        }
        return RespBean.error("上传失败！");
    }


    @PostMapping("/top/search/{pageNum}/{pageSize}")
    public RespEmpBean findByPage(@PathVariable("pageNum") Integer pageNum,
                                  @PathVariable("pageSize") Integer pageSize, Employee employee) {
        RespEmpBean reb = new RespEmpBean();
        List<Employee> list = empLoyeeService.selectByPageAndEmployee(pageNum - 1, pageSize, employee);
        int i = empLoyeeService.selectByEmployeeCount(employee);
        reb.setEmployees(list);
        reb.setTotal(i);
        return reb;
    }
}
