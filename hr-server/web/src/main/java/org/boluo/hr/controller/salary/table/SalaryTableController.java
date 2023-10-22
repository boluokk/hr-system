package org.boluo.hr.controller.salary.table;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.pojo.SalaryTableSearch;
import org.boluo.hr.pojo.SalaryTableView;
import org.boluo.hr.service.SalaryTableService;
import org.boluo.hr.service.util.ExportImportExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 工资报表
 *
 * @author 🍍
 * @date 2023/10/22
 */

@RestController
@RequestMapping("/sal/table")
public class SalaryTableController {

    private final SalaryTableService salaryTableService;

    @Autowired
    public SalaryTableController(SalaryTableService salaryTableService) {
        this.salaryTableService = salaryTableService;
    }

    /**
     * 返回所有工资表
     */
    @PostMapping("/{pageNum}/{pageSize}")
    public RespBean findPage(@PathVariable("pageNum") Integer pageNum,
                             @PathVariable("pageSize") Integer pageSize,
                             SalaryTableSearch salaryTableSearch) {
        PageHelper.startPage(pageNum, pageSize);
        return RespBean.ok(new PageInfo<>(salaryTableService.selectAll(salaryTableSearch)));
    }

    /**
     * 导出员工的工资表
     */
    @PostMapping("/export/workId/date")
    public ResponseEntity<byte[]> exportSalaryTable(@PathVariable("workId") String workId,
                                                    @PathVariable("date") Date date) {
        List<SalaryTableView> salaryTableViews = salaryTableService.selectAll(
                new SalaryTableSearch().setWorkId(workId).setDate(date));
        try {
            return ExportImportExcelUtil.exportSalaryTable(salaryTableViews.get(0));
        } catch (IOException e) {
            return null;
        }
    }
}
