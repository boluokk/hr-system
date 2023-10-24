package org.boluo.hr.controller.system.basic;

import org.boluo.hr.pojo.Position;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 职位信息
 *
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

    /**
     * 查询所有职位
     */
    @GetMapping("/")
    public RespBean findAllPos() {
        return RespBean.ok(positionService.selectAllPosition());
    }

    /**
     * 返回职位
     */
    @GetMapping("/byId/{id}")
    public RespBean findPosById(@PathVariable Integer id) {
        return RespBean.ok(positionService.selectPositionById(id));
    }

    /**
     * 删除职位
     */
    @DeleteMapping("/delete/{id}")
    public RespBean remove(@PathVariable Integer id) {
        if (positionService.delete(id)) {
            return RespBean.ok();
        } else {
            return RespBean.error();
        }
    }

    /**
     * 新增职位
     */
    @PutMapping("/add")
    public RespBean add(@RequestBody Position position) {
        position.setEnabled(true);
        if (position.getCreateDate() == null) {
            position.setCreateDate(new Date());
        }
        if (positionService.insert(position)) {
            return RespBean.ok();
        } else {
            return RespBean.error();
        }
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/many/")
    public RespBean deleteMany(@RequestBody Integer[] ids) {
        if (positionService.deleteMany(ids)) {
            return RespBean.ok();
        } else {
            return RespBean.error();
        }
    }

    /**
     * 修改职位
     */
    @PutMapping("/modify")
    public RespBean modify(@RequestBody Position pos) {
        if (positionService.update(pos)) {
            return RespBean.ok();
        } else {
            return RespBean.error();
        }
    }
}
