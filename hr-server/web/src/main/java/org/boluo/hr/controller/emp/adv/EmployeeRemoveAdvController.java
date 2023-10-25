package org.boluo.hr.controller.emp.adv;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.boluo.hr.annotation.Log;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.service.EmployeeRemoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 调岗信息
 *
 * @author 🍍
 * @date 2023/10/1
 */
@RestController
@RequestMapping("/emp/adv/remove")
public class EmployeeRemoveAdvController {

    private final EmployeeRemoveService employeeremoveService;

    @Autowired
    public EmployeeRemoveAdvController(EmployeeRemoveService employeeremoveService) {
        this.employeeremoveService = employeeremoveService;
    }

    /**
     * 调岗分页
     */
    @GetMapping("/{pageNum}/{pageSize}")
    @Log("查询调岗分页")
    public RespBean findByPage(@PathVariable("pageNum") Integer pageNum,
                               @PathVariable("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return RespBean.ok(new PageInfo<>(employeeremoveService.selectAll()));
    }
}
