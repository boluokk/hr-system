package org.boluo.hr.controller.personnel.emp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.boluo.hr.pojo.Employee;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.service.*;
import org.boluo.hr.service.util.ExportAndImportExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 员工信息
 *
 * @author 🍍
 * @date 2023/10/1
 */
@RestController
@RequestMapping("/personnel/emp")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final NationService nationService;
    private final DepartmentService departmentService;
    private final PoliticsStatusService politicsStatusService;
    private final JobLevelService jobLevelService;
    private final PositionService positionService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, NationService nationService,
                              DepartmentService departmentService, PoliticsStatusService politicsStatusService,
                              JobLevelService jobLevelService, PositionService positionService) {
        this.employeeService = employeeService;
        this.nationService = nationService;
        this.departmentService = departmentService;
        this.politicsStatusService = politicsStatusService;
        this.jobLevelService = jobLevelService;
        this.positionService = positionService;
    }

    private final static String PATTERN = "^[0-9]{8}";

    /**
     * 员工分页
     */
    @GetMapping("/{pageNum}/{pageSize}")
    public RespBean findPages(@PathVariable("pageNum") Integer pageNum,
                              @PathVariable("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Employee> employees = employeeService.selectAll();
        return RespBean.ok(new PageInfo<>(employees));
    }


    /**
     * 通过名称返回员工信息
     */
    @GetMapping("/byEmpName/{empName}")
    public RespBean findEmpByEmpName(@PathVariable("empName") String empName) {
        return RespBean.ok(employeeService.selectByEmpName(empName));
    }

    /**
     * 所有民族
     */
    @Deprecated
    @GetMapping("/nation")
    public RespBean findAllNation() {
        return RespBean.ok(employeeService.selectNations());
    }

    /**
     * 添加员工
     */
    @PutMapping("/add")
    public RespBean add(Employee employee) {
        boolean matches = Pattern.matches(PATTERN, employee.getWorkId());
        if (!matches) {
            return RespBean.error("workId不能为数字!!!");
        }
        if (employeeService.insertOne(employee)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    /**
     * 修改员工
     *
     * @param employee 员工信息
     */
    @PutMapping("/modify")
    public RespBean modify(Employee employee) {
        boolean matches = Pattern.matches(PATTERN, employee.getWorkId());
        if (!matches) {
            return RespBean.error("workId不能为数字!!!");
        }
        if (employeeService.update(employee)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    /**
     * 最大员工号
     */
    @GetMapping("/maxWorkId")
    public RespBean findMaxWorkId() {
        Object format = String.format("%08d", employeeService.selectMaxByWorkId());
        return RespBean.ok(format);
    }

    /**
     * 删除员工
     */
    @DeleteMapping("/delete/{id}")
    public RespBean delete(@PathVariable Integer id) {
        if (employeeService.delete(id)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    /**
     * 导出员工信息
     */
    @GetMapping("/export")
    public ResponseEntity<byte[]> export() throws IOException {
        List<Employee> list = employeeService.selectAll();
        return ExportAndImportExcelUtil.exportData(list);
    }

    /**
     * 导入员工信息
     */
    @PostMapping("/import")
    public RespBean importEmployees(MultipartFile file) throws IOException {
        List<Employee> employees =
                ExportAndImportExcelUtil.importData(file, nationService.selectAllNation(),
                        departmentService.selectAll(), politicsStatusService.selectAllPolitic(),
                        jobLevelService.selectAll(), positionService.selectAllPosition());
        if (employeeService.batchInsert(employees) == employees.size()) {
            return RespBean.ok("上传成功！");
        }
        return RespBean.error("上传失败！");
    }


    /**
     * 条件员工分页
     */
    @PostMapping("/top/search/{pageNum}/{pageSize}")
    public RespBean findByPage(@PathVariable("pageNum") Integer pageNum,
                               @PathVariable("pageSize") Integer pageSize, Employee employee) {
        PageHelper.startPage(pageNum, pageSize);
        return RespBean.ok(new PageInfo<>(employeeService.selectByPageAndEmployee(employee)));
    }
}
