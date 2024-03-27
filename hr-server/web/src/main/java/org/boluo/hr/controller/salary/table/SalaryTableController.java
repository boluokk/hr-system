package org.boluo.hr.controller.salary.table;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.bluo.hr.service.api.HrServiceClient;
import org.bluo.hr.service.api.pojo.SalaryTableSearch;
import org.boluo.hr.annotation.Log;
import org.bluo.global.pojo.RespBean;
import org.boluo.hr.pojo.SalaryTableView;
import org.boluo.hr.service.SalaryTableService;
import org.boluo.hr.util.ExportImportExcelUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * 工资报表
 *
 * @author 🍍
 * @date 2023/10/22
 */

@RestController
@RequestMapping("/sal/table")
public class SalaryTableController implements HrServiceClient {

    @Resource
    private SalaryTableService salaryTableService;

    /**
     * 返回所有工资表
     */
    @PostMapping("/{pageNum}/{pageSize}")
    @Log("查询所有工资表")
    @Override
    public RespBean findPage(@PathVariable("pageNum") Integer pageNum,
                             @PathVariable("pageSize") Integer pageSize,
                             @Valid @RequestBody SalaryTableSearch salaryTableSearch) {
        PageHelper.startPage(pageNum, pageSize);
        return RespBean.ok(new PageInfo<>(salaryTableService.selectAll(salaryTableSearch)));
    }

    /**
     * 导出员工的工资表
     */
    @PostMapping("/export")
    @Log("导出员工的工资表")
    public ResponseEntity<byte[]> exportSalaryTable(@Valid @RequestBody SalaryTableSearch salaryTableSearch) {
        List<SalaryTableView> salaryTableViews = salaryTableService.selectAll(salaryTableSearch);
        try {
            return ExportImportExcelUtil.exportSalaryTable(salaryTableViews);
        } catch (IOException e) {
            return null;
        }
    }

}
