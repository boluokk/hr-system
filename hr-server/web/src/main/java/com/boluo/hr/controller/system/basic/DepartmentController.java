package com.boluo.hr.controller.system.basic;

import com.boluo.hr.pojo.DepartRequestBean;
import com.boluo.hr.pojo.Department;
import com.boluo.hr.pojo.RespBean;
import com.boluo.hr.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author @1352955539(boluo)
 * @date 2021/2/8 - 23:07
 */
@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/")
    public RespBean showDep() {
        List<Department> list = departmentService.getAllDepart(-1);
        return RespBean.ok(list);
    }

    // 需要字段 parentId childrenName childrenEnabled
    @Transactional(rollbackFor = Exception.class)
    @PutMapping("/")
    public RespBean addDep(DepartRequestBean departRequestBean) throws Exception {
        if (!departRequestBean.getParentIsParent()) {
            Department department = new Department();
            department.setId(departRequestBean.getParentId());
            department.setIsparent(true);
            int i = departmentService.updateDeparOfOne(department);
            if (i == 0) {
                throw new Exception("失败！");
            }
        }
        Department department = new Department();
        department.setName(departRequestBean.getChildrenName());
        department.setParentid(departRequestBean.getParentId());
        department.setEnabled(departRequestBean.getChildrenEnabled());
        department.setIsparent(false);
        int i = departmentService.insertDeparOfOne(department);
        if (i == 0) {
            throw new Exception("失败！");
        }
        int lastinserid = departmentService.last_insert_id();
        if (lastinserid == 0) {
            throw new Exception("失败！");
        }
        Department department1 = new Department();
        department1.setId(lastinserid);
        department1.setDeppath(departRequestBean.getParentDepPath() + "." + lastinserid);
        int i1 = departmentService.updateDeparOfOne(department1);
        if (i1 == 0) {
            throw new Exception("失败！");
        }
        return RespBean.ok("修改成功！");
    }

    // 需要传入的值 parentdeppath parentid
    @Transactional(rollbackFor = Exception.class)
    @DeleteMapping("/delete")
    public RespBean delDep(@RequestBody DepartRequestBean departRequestBean) throws Exception {
        int i = departmentService.deleteBydepPath(departRequestBean.getParentDepPath());
        if (i == 0) {
            throw new Exception("失败！11");
        }
        int i1 = departmentService.selectCountbyParenId(departRequestBean.getParentId());
        if (i1 == 0) {
            Department department = new Department();
            department.setId(departRequestBean.getParentId());
            department.setIsparent(false);
            int i2 = departmentService.updateDeparOfOne(department);
            if (i2 == 0) {
                throw new Exception("失败！22");
            }
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("错误！");
    }

    @GetMapping("/enab/f")
    public RespBean showDepEnabledIsF() {
        return RespBean.ok(departmentService.getAllDepWithEnabledIsFalse());
    }

    @PutMapping("/change/one")
    public RespBean changeEnab(Department department) {
        int i = departmentService.updateDeparOfOne(department);
        if (i == 1) {
            return RespBean.ok("修改成功！");
        } else {
            return RespBean.error("修改失败！");
        }
    }

    @GetMapping("/search/{name}")
    public RespBean showOneEnabF(@PathVariable String name) {
        List<Department> oneBysearch = departmentService.getOneBysearch(name);
        return RespBean.ok(oneBysearch);
    }

    @GetMapping("/all")
    public RespBean showDepBy() {
        List<Department> allDepInfo = departmentService.getAllDepInfo();
        return RespBean.ok(allDepInfo);
    }
}
