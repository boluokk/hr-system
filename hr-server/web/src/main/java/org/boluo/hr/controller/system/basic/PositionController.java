package org.boluo.hr.controller.system.basic;

import org.boluo.hr.annotation.Log;
import org.boluo.hr.pojo.InsertPosition;
import org.boluo.hr.pojo.RespBean;
import org.boluo.hr.pojo.UploadPosition;
import org.boluo.hr.service.PositionService;
import org.boluo.hr.util.CheckUtil;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Date;

/**
 * 职位信息
 *
 * @author 🍍
 * @date 2023/10/1
 */

@RestController
@RequestMapping("/system/basic/job")
@Validated
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
    @Log("查询所有职位")
    public RespBean findAllPos() {
        return RespBean.ok(positionService.selectAllPosition());
    }

    /**
     * 通过id 返回职位
     */
    @GetMapping("/byId/{id}")
    @Log("通过id查询职位")
    public RespBean findPosById(@Min(value = 1, message = "id必须大于0")
                                @PathVariable("id") Integer id) {
        return RespBean.ok(positionService.selectPositionById(id));
    }

    /**
     * 删除职位
     */
    @DeleteMapping("/delete/{id}")
    @Log("删除职位")
    public RespBean remove(@Min(value = 1, message = "id必须大于0")
                           @PathVariable("id") Integer id) {
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
    @Log("新增职位")
    public RespBean add(@Valid @RequestBody InsertPosition insertPosition) {
        insertPosition.setEnabled(true);
        if (CheckUtil.isNull(insertPosition.getCreateDate())) {
            insertPosition.setCreateDate(new Date());
        }
        if (positionService.insert(insertPosition)) {
            return RespBean.ok();
        } else {
            return RespBean.error();
        }
    }

    /**
     * 批量删除职位
     */
    @DeleteMapping("/delete/many/")
    @Log("批量删除职位")
    public RespBean deleteMany(@Length(min = 1, message = "至少需要一个id")
                               @RequestBody Integer[] ids) {
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
    @Log("修改职位")
    public RespBean modify(@Valid @RequestBody UploadPosition uploadPosition) {
        if (positionService.update(uploadPosition)) {
            return RespBean.ok();
        } else {
            return RespBean.error();
        }
    }
}
