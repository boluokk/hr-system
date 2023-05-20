package com.boluo.hr.controller.system.hr;

import com.boluo.hr.pojo.Hr;
import com.boluo.hr.pojo.RespBean;
import com.boluo.hr.pojo.Role;
import com.boluo.hr.service.HrService;
import com.boluo.hr.service.RightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author @1352955539(boluo)
 * @date 2021/2/25 - 23:35
 */
@RestController
@RequestMapping("/sys/hr")
public class SystemHr {

    @Autowired
    private HrService hrService;

    @Autowired
    private RightsService rightsService;

    @GetMapping("/")
    public List<Hr> showHrs() {
        return hrService.getHrs();
    }

    @GetMapping("/{hid}")
    public List<Role> getRoles(@PathVariable("hid") Integer hid) {
        return hrService.getRoles(hid);
    }

    @GetMapping("/all/roles")
    public List<Role> getAllrRoles() {
        return rightsService.getAllRole();
    }

    @Transactional(rollbackFor = Exception.class)
    @PutMapping("/roles/many/{hrid}")
    public RespBean editRoles(@PathVariable("hrid") Integer hrid, Integer[] rolesId) {
        int i = hrService.deleteRoleByHrid(hrid);
        if (i >= 0) {
            if (rolesId.length > 0) {
                int i1 = hrService.insertRoles(hrid, rolesId);
                if (i1 >= 1) {
                    return RespBean.ok("修改成功！");
                }
                return RespBean.error("修改失败！");
            } else {
                return RespBean.ok("修改成功！");
            }
        }
        return RespBean.error("修改失败！");
    }

    @PutMapping("/")
    public RespBean editHrEnabeld(Hr hr) {
        int i = hrService.updateHr(hr);
        if (i == 1) {
            return RespBean.ok("修改成功！");
        }
        return RespBean.ok("修改失败！");
    }

    @DeleteMapping("/{hrid}")
    public RespBean deleteHr(@PathVariable("hrid") Integer hrid) {
        int i = hrService.deleteHr(hrid);
        if (i == 1) {
            return RespBean.ok("删除成功！");
        }
        return RespBean.ok("删除失败！");
    }

    @PostMapping("/hrname")
    public List<Hr> someHrByHrName(Hr hr) {
        return hrService.selectSomeHrsByHrName(hr.getName());
    }

    @PutMapping("/one/")
    public RespBean addHr(Hr hr) {
        BCryptPasswordEncoder bCryp = new BCryptPasswordEncoder();
        hr.setPassword(bCryp.encode(hr.getPassword()));
        int i = hrService.addHr(hr);
        if (i == 1) {
            return RespBean.ok("新建成功！");
        }
        return RespBean.ok("新建失败！");
    }
}
