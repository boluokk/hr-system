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

    @GetMapping("/")
    public RespBean findDepartment() {
        return RespBean.ok(departmentService.selectAllDepart(-1));
    }

    // 需要字段 parentId childrenName childrenEnabled
    @Transactional(rollbackFor = Exception.class)
    @PutMapping("/")
    public RespBean add(DepartRequestBean departRequestBean) {
        if (!departRequestBean.getParentIsParent()) {
            Department department = new Department();
            department.setId(departRequestBean.getParentId());
            department.setIsparent(true);
            if (!departmentService.update(department)) {
                throw new BusinessException("更新部门失败");
            }
        }
        Department department = new Department();
        department.setName(departRequestBean.getChildrenName());
        department.setParentid(departRequestBean.getParentId());
        department.setEnabled(departRequestBean.getChildrenEnabled());
        department.setIsparent(false);
        if (!departmentService.insert(department)) {
            throw new BusinessException("添加失败");
        }
        int lastinserid = departmentService.lastInsertId();
        if (lastinserid == 0) {
            throw new BusinessException("最新id为0异常");
        }
        Department newDepartment = new Department();
        newDepartment.setId(lastinserid);
        newDepartment.setDeppath(departRequestBean.getParentDepPath() + "." + lastinserid);
        if (!departmentService.update(newDepartment)) {
            throw new BusinessException("更新部门失败！");
        }
        return RespBean.ok();
    }

    // 需要传入的值 parentdeppath parentid
    @Transactional(rollbackFor = Exception.class)
    @DeleteMapping("/delete")
    public RespBean remove(@RequestBody DepartRequestBean departRequestBean) {
        if (!departmentService.deleteByDepPath(departRequestBean.getParentDepPath())) {
            throw new BusinessException("删除路径失败");
        }
        if (!departmentService.selectCountByParenId(departRequestBean.getParentId())) {
            Department department = new Department();
            department.setId(departRequestBean.getParentId());
            department.setIsparent(false);
            if (!departmentService.update(department)) {
                throw new BusinessException("部门更新失败");
            }
            return RespBean.ok();
        }
        return RespBean.error();
    }

    @GetMapping("/enab/f")
    public RespBean findDepEnabledOrFalse() {
        return RespBean.ok(departmentService.selectAllDepWithDisabled());
    }

    @PutMapping("/change/one")
    public RespBean modify(Department department) {
        if (departmentService.update(department)) {
            return RespBean.ok();
        } else {
            return RespBean.error();
        }
    }

    @GetMapping("/search/{name}")
    public RespBean findEnabled(@PathVariable String name) {
        return RespBean.ok(departmentService.selectByName(name));
    }

    @GetMapping("/all")
    public RespBean findAll() {
        return RespBean.ok(departmentService.selectAll());
    }
}
