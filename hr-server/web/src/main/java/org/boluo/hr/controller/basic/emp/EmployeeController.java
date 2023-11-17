package org.boluo.hr.controller.basic.emp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.boluo.hr.annotation.Log;
import org.boluo.hr.pojo.Employee;
import org.boluo.hr.pojo.InsertEmployee;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.pojo.UploadEmployee;
import org.boluo.hr.service.*;
import org.boluo.hr.util.ExportImportExcelUtil;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

/**
 * 员工信息
 *
 * @author 🍍
 * @date 2023/10/1
 */
@RestController
@RequestMapping("/basic/emp")
@Validated
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

    /**
     * 员工分页
     */
    @GetMapping("/{pageNum}/{pageSize}")
    @Log("查询员工分页")
    public RespBean findPages(@Min(value = 1, message = "页码不能小于1")
                              @PathVariable("pageNum") Integer pageNum,
                              @Range(min = 1, max = 10, message = "页大小为1-10")
                              @PathVariable("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Employee> employees = employeeService.selectAll();
        return RespBean.ok(new PageInfo<>(employees));
    }


    /**
     * 通过名称返回员工信息
     */
    @GetMapping("/byEmpName/{empName}/{pageNum}/{pageSize}")
    @Log("通过名称返回员工信息")
    public RespBean findEmpByEmpName(@PathVariable("empName") String empName,
                                     @Min(value = 1, message = "页码不能小于1")
                                     @PathVariable("pageNum") Integer pageNum,
                                     @Range(min = 1, max = 10, message = "页大小为1-10")
                                     @PathVariable("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return RespBean.ok(new PageInfo<>(employeeService.selectByEmpName(empName)));
    }

    /**
     * 所有民族
     */
    @Deprecated
    @GetMapping("/nation")
    @Log("查询所有民族")
    public RespBean findAllNation() {
        return RespBean.ok(employeeService.selectNations());
    }

    /**
     * 添加员工
     */
    @PutMapping("/add")
    @Log("添加员工")
    public RespBean add(@Valid @RequestBody InsertEmployee insertEmployee) {
        if (employeeService.insertOne(insertEmployee)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    /**
     * 修改员工
     */
    @PutMapping("/modify")
    @Log("修改员工")
    public RespBean modify(@Valid @RequestBody UploadEmployee uploadEmployee) {
        if (employeeService.update(uploadEmployee)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    /**
     * 删除员工
     */
    @DeleteMapping("/delete/{id}")
    @Log("删除员工")
    public RespBean delete(@Min(value = 1, message = "id最小为1")
                           @PathVariable("id") Integer id) {
        if (employeeService.delete(id)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    /**
     * 导出员工信息
     */
    @GetMapping("/export")
    @Log("导出员工信息")
    public ResponseEntity<byte[]> export() throws IOException {
        List<Employee> list = employeeService.selectAll();
        return ExportImportExcelUtil.exportEmployeeData(list);
    }

    /**
     * 导入员工信息
     */
    @PostMapping("/import")
    @Log("导入员工信息")
    public RespBean importEmployees(@NotNull(message = "文件不能为空")
                                    MultipartFile file) throws IOException {
        List<Employee> employees =
                ExportImportExcelUtil.importEmployeeData(file, nationService.selectAllNation(),
                        departmentService.selectAll(), politicsStatusService.selectAllPolitic(),
                        jobLevelService.selectAll(), positionService.selectAllPosition());
        if (employeeService.batchInsert(employees) == employees.size()) {
            return RespBean.ok("上传成功", true);
        }
        return RespBean.error("上传失败！");
    }


    /**
     * 条件员工分页
     */
    @PostMapping("/top/search/{pageNum}/{pageSize}")
    @Log("查询条件员工分页")
    public RespBean findByPage(@Min(value = 1, message = "页码不能小于1")
                               @PathVariable("pageNum") Integer pageNum,
                               @Range(min = 1, max = 10, message = "页大小不能小于1或大于10")
                               @PathVariable("pageSize") Integer pageSize,
                               @RequestBody UploadEmployee uploadEmployee) {
        PageHelper.startPage(pageNum, pageSize);
        return RespBean.ok(new PageInfo<>(employeeService.selectByPageAndEmployee(uploadEmployee)));
    }
}
