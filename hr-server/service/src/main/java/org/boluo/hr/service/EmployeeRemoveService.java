package org.boluo.hr.service;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.ObjectUtil;
import org.boluo.hr.exception.BusinessException;
import org.boluo.hr.mapper.EmployeeRemoveMapper;
import org.boluo.hr.pojo.EmployeeRemoveView;
import org.boluo.hr.pojo.InsertEmployeeRemove;
import org.boluo.hr.pojo.UploadEmployee;
import org.boluo.hr.pojo.UploadEmployeeRemove;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 调岗 业务层
 *
 * @author 🍍
 * @date 2023/10/1
 */
@Service
public class EmployeeRemoveService {
    @Resource
    private EmployeeRemoveMapper employeeRemoveMapper;

    @Resource
    private EmployeeService employeeService;

    /**
     * 查询所有调岗
     *
     * @return 调岗集合
     */
    public List<EmployeeRemoveView> selectAll() {
        return employeeRemoveMapper.selectAll();
    }

    /**
     * 删除调岗
     *
     * @param id 调岗id
     * @return 结果
     */
    public boolean delete(Integer id) {
        return employeeRemoveMapper.deleteByPrimaryKey(id) == 1;
    }

    /**
     * 修改调岗
     *
     * @param uploadEmployeeRemove 调岗信息
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean update(UploadEmployeeRemove uploadEmployeeRemove) {
        return employeeRemoveMapper.updateByPrimaryKey(uploadEmployeeRemove) == 1;
    }

    /**
     * 新增调岗
     *
     * @param insertEmployeeRemove 调岗信息
     * @return 结果
     */
    public boolean insert(InsertEmployeeRemove insertEmployeeRemove) {
        return employeeRemoveMapper.insertEmployeeRemove(insertEmployeeRemove) == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean updateOldEmployee(UploadEmployee uploadEmployee, InsertEmployeeRemove insertEmployeeRemove) {
        if (employeeService.update(uploadEmployee) && insert(insertEmployeeRemove)) {
            return true;
        } else {
            throw new BusinessException("操作失败");
        }
    }

}
