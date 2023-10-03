package org.boluo.hr.controller.system.basic;

import org.boluo.hr.pojo.Position;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author 🍍
 * @date 2023/10/1
 */

@RestController
@RequestMapping("/system/basic/job")
public class PositionController {

    private final PositionService positionService;

    @Autowired
    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping("/")
    public RespBean getAllPos() {
        return RespBean.ok(positionService.selectAllPostion());
    }

    @GetMapping("/{id}")
    public RespBean getPosById(@PathVariable Integer id) {
        return RespBean.ok(positionService.selectPostById(id));
    }

    @DeleteMapping("/{id}")
    public RespBean remove(@PathVariable Integer id) {
        if (positionService.delete(id)) {
            return RespBean.ok();
        } else {
            return RespBean.error();
        }
    }

    @PutMapping("/")
    public RespBean add(Position pos) {
        pos.setEnabled(true);
        pos.setCreatedate(new Date());
        if (positionService.insert(pos)) {
            return RespBean.ok();
        } else {
            return RespBean.error();
        }
    }

    @DeleteMapping("/many/")
    public RespBean deleteMany(Integer[] ids) {
        if (positionService.deleteMany(ids)) {
            return RespBean.ok();
        } else {
            return RespBean.error();
        }
    }

    @PutMapping("/change")
    public RespBean modify(Position pos) {
        if (positionService.update(pos)) {
            return RespBean.ok();
        } else {
            return RespBean.error();
        }
    }
}
