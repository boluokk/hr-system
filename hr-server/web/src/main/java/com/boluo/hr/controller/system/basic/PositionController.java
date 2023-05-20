package com.boluo.hr.controller.system.basic;

import com.boluo.hr.pojo.Position;
import com.boluo.hr.pojo.RespBean;
import com.boluo.hr.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author @1352955539(boluo)
 * @date 2021/2/4 - 22:09
 */

@RestController
@RequestMapping("/system/basic/job")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @GetMapping("/")
    public RespBean getAllPos() {
        return RespBean.ok(positionService.getAllPostion());
    }

    @GetMapping("/{id}")
    public RespBean getPosById(@PathVariable Integer id) {
        return RespBean.ok(positionService.getPostByid(id));
    }

    @DeleteMapping("/{id}")
    public RespBean deleteById(@PathVariable Integer id) {
        int i = positionService.deleteById(id);
        if(i==1) {
            return RespBean.ok("删除成功！");
        }else {
            return RespBean.error("删除失败！");
        }
    }

    @PutMapping("/")
    public RespBean insertByPos(Position pos) {
        pos.setEnabled(true);
        pos.setCreatedate(new Date());
        int i = positionService.insertByPos(pos);
        if(i==1) {
            return RespBean.ok("添加成功！");
        }else {
            return RespBean.error("添加失败！");
        }
    }

    @DeleteMapping("/many/")
    public RespBean deleteByMany(Integer[] ids) {
        int i = positionService.deleteMany(ids);
        if(i==ids.length) {
            return RespBean.ok("删除成功！");
        }else {
            return RespBean.error("删除失败！");
        }
    }

    @PutMapping("/change")
    public RespBean updatePos(Position pos) {
        int i = positionService.updateByPos(pos);
        if(i==1) {
            return RespBean.ok("修改成功！");
        }else {
            return RespBean.error("修改失败！");
        }
    }
}
