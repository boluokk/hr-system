package org.boluo.hr.controller.system.basic;

import org.boluo.hr.exception.BusinessException;
import org.boluo.hr.pojo.DepartRequestBean;
import org.boluo.hr.pojo.Department;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * 部门信息
 *
 * @author 🍍
 * @date 2023/10/1
 */
@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * 所有部门
     */
    @GetMapping("/root")
    public RespBean findDepartment() {
        return RespBean.ok(departmentService.selectAllDepart(-1));
    }

    /**
     * 添加部门
     */
    @Transactional(rollbackFor = Exception.class)
    @PutMapping("/add")
    public RespBean add(@RequestBody DepartRequestBean departRequestBean) {
        if (!departRequestBean.getParentIsParent()) {
            Department department = new Department().setId(departRequestBean.getParentId()).setIsParent(true);
            if (!departmentService.update(department)) {
                throw new BusinessException("更新部门失败");
            }
        }
        Department department = new Department()
                .setName(departRequestBean.getChildrenName())
                .setParentId(departRequestBean.getParentId())
                .setEnabled(departRequestBean.getChildrenEnabled())
                .setIsParent(false);
        if (!departmentService.insert(department)) {
            throw new BusinessException("添加失败");
        }
        int lastInsertId = departmentService.lastInsertId();
        if (lastInsertId == 0) {
            throw new BusinessException("新插入部门id不能为0");
        }
        Department newDepartment = new Department()
                .setId(lastInsertId)
                // 拼接部门路径
                .setDepPath(departRequestBean.getParentDepPath() + "." + lastInsertId);
        if (!departmentService.update(newDepartment)) {
            throw new BusinessException("更新部门失败！");
        }
        return RespBean.ok();
    }

    /**
     * 删除部门
     */
    @Transactional(rollbackFor = Exception.class)
    @DeleteMapping("/delete")
    public RespBean remove(@RequestBody DepartRequestBean departRequestBean) {
        if (!departmentService.deleteByDepPath(departRequestBean.getParentDepPath())) {
            throw new BusinessException("删除路径失败");
        }
        if (!departmentService.noChildren(departRequestBean.getParentId())) {
            if (!departmentService.update(new Department().setId(departRequestBean.getParentId()).setIsParent(false))) {
                throw new BusinessException("部门更新失败");
            }
            return RespBean.ok();
        }
        return RespBean.error();
    }

    /**
     * 关闭的部门
     */
    @GetMapping("/enabled")
    public RespBean findDisabledDepartment() {
        return RespBean.ok(departmentService.selectAllDepWithDisabled());
    }

    /**
     * 修改部门
     */
    @PutMapping("/modify")
    public RespBean modify(@RequestBody Department department) {
        if (departmentService.update(department)) {
            return RespBean.ok();
        } else {
            return RespBean.error();
        }
    }

    /**
     * 部门名查询部门
     */
    @GetMapping("/search/{name}")
    public RespBean findByName(@PathVariable String name) {
        return RespBean.ok(departmentService.selectByName(name));
    }

    /**
     * 所有部门
     */
    @GetMapping("/")
    public RespBean findAll() {
        return RespBean.ok(departmentService.selectAll());
    }
}
