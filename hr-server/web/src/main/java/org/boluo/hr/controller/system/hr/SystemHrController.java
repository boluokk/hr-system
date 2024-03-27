package org.boluo.hr.controller.system.hr;

import org.bluo.global.pojo.RespBean;
import org.boluo.hr.annotation.Log;
import org.boluo.hr.pojo.*;
import org.boluo.hr.service.HrService;
import org.boluo.hr.service.RightsService;
import org.boluo.hr.util.CheckUtil;
import org.boluo.hr.util.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * 人事信息
 *
 * @author 🍍
 * @date 2023/10/1
 */
@RestController
@RequestMapping("/sys/hr")
@Validated
public class SystemHrController {

    @Resource
    private HrService hrService;
    @Resource
    private RightsService rightsService;

    /**
     * 返回所有人事信息
     */
    @GetMapping("/")
    @Log("查询所有人事信息")
    public List<Hr> findHrs() {
        return hrService.selectAll();
    }

    /**
     * 返回人事所含角色
     */
    @GetMapping("/byHrId/{hrId}")
    @Log("查询人事所含角色")
    public List<Role> findRoles(@Min(value = 1, message = "hrId必须大于 0")
                                @PathVariable("hrId") Integer hrId) {
        return hrService.selectRoles(hrId);
    }

    /**
     * 返回所有角色
     */
    @GetMapping("/all/roles")
    @Log("查询所有角色")
    public List<Role> findAllRoles() {
        return rightsService.selectAllRoles();
    }

    /**
     * 修改人事角色
     */
    @PutMapping("/many/roles")
    @Log("修改人事角色")
    public RespBean modifyRoles(@Valid @RequestBody UploadHrRole uploadHrRole) {
        if (hrService.modifyRoles(uploadHrRole)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    /**
     * 修改人事信息
     */
    @PutMapping("/modify")
    @Log("修改人事信息")
    public RespBean modifyHr(@Valid @RequestBody UploadHr hr, HttpSession session) {
        String password = hr.getPassword();
        if (CheckUtil.hasLength(password)) {
            hr.setPassword(PasswordEncoder.encode(password));
        } else {
            hr.setPassword(null);
        }
        if (hrService.update(hr)) {
            // session 失效
            if (hr.getPassword() != null) {
                if (CheckUtil.isNotNull(session)) {
                    session.invalidate();
                }
            }
            return RespBean.ok();
        }
        return RespBean.error();

    }

    /**
     * 删除人事
     */
    @DeleteMapping("/delete/{hrId}")
    @Log("删除人事")
    public RespBean removeHr(@Min(value = 1, message = "hrId必须大于 0")
                             @PathVariable("hrId") Integer hrId) {
        Hr hr = hrService.selectById(hrId);
        if (CheckUtil.isNotNull(hr) && "admin".equals(hr.getUsername())) {
            return RespBean.error("admin用户不能删除");
        }
        if (hrService.delete(hrId) && hrService.findRoleAfterDelete(hrId)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    /**
     * 查询人事 通过人事名称
     */
    @GetMapping("/byHrName/{hrName}")
    @Log("通过人事名称查询人事")
    public RespBean findHrByName(@PathVariable("hrName") String hrName) {
        return RespBean.ok(hrService.selectHrByName(hrName));
    }

    /**
     * 新增人事
     */
    @PutMapping("/add")
    @Log("新增人事")
    public RespBean add(@Valid @RequestBody InsertHr insertHr) {
        if (!CheckUtil.isNull(hrService.selectByUsername(insertHr.getUsername()))) {
            return RespBean.error("当前用户名已经存在");
        }
        insertHr.setPassword(PasswordEncoder.encode(insertHr.getPassword()));
        if (hrService.insert(insertHr)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }
}
