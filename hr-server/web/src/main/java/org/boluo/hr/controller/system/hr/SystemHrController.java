package org.boluo.hr.controller.system.hr;

import org.boluo.hr.pojo.Hr;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.pojo.Role;
import org.boluo.hr.service.HrService;
import org.boluo.hr.service.RightsService;
import org.boluo.hr.util.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 人事信息
 *
 * @author 🍍
 * @date 2023/10/1
 */
@RestController
@RequestMapping("/sys/hr")
public class SystemHrController {

    private final HrService hrService;

    private final RightsService rightsService;

    @Autowired
    public SystemHrController(HrService hrService, RightsService rightsService) {
        this.hrService = hrService;
        this.rightsService = rightsService;
    }

    @GetMapping("/")
    public List<Hr> findHrs() {
        return hrService.selectAll();
    }

    @GetMapping("/byHrId/{hid}")
    public List<Role> findRoles(@PathVariable("hid") Integer hid) {
        return hrService.selectRoles(hid);
    }

    @GetMapping("/all/roles")
    public List<Role> findAllRoles() {
        return rightsService.selectAllRoles();
    }

    @Transactional(rollbackFor = Exception.class)
    @PutMapping("/many/roles/{hrId}")
    public RespBean modifyRoles(@PathVariable("hrId") Integer hrId, Integer[] rolesId) {
        if (hrService.deleteRoleByHrId(hrId)) {
            if (rolesId.length > 0) {
                if (hrService.insertRoles(hrId, rolesId)) {
                    return RespBean.ok();
                }
                return RespBean.error();
            } else {
                return RespBean.ok();
            }
        }
        return RespBean.error();
    }

    @PutMapping("/modify")
    public RespBean modifyHr(Hr hr, HttpSession session) {
        String password = hr.getPassword();
        if (password != null && !password.isEmpty()) {
            hr.setPassword(PasswordEncoder.encode(password));
        } else {
            hr.setPassword(null);
        }
        if (hrService.update(hr)) {
            // session 失效
            if (hr.getPassword() != null) {
                session.invalidate();
            }
            return RespBean.ok();
        }
        return RespBean.error();

    }

    @DeleteMapping("/delete/{hrId}")
    public RespBean removeHr(@PathVariable("hrId") Integer hrId) {
        if (hrService.delete(hrId)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }

    @GetMapping("/byHrName/{hrName}")
    public List<Hr> findHrByName(@PathVariable("hrName") String hrName) {
        return hrService.selectHrByName(hrName);
    }

    @PutMapping("/add")
    public RespBean add(Hr hr) {
        hr.setPassword(PasswordEncoder.encode(hr.getPassword()));
        if (hrService.insert(hr)) {
            return RespBean.ok();
        }
        return RespBean.error();
    }
}
