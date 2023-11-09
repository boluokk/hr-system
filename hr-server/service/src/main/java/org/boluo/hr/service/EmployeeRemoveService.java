package org.boluo.hr.service;

import org.boluo.hr.mapper.EmployeeRemoveMapper;
import org.boluo.hr.pojo.EmployeeRemoveView;
import org.boluo.hr.pojo.InsertEmployeeRemove;
import org.boluo.hr.pojo.UploadEmployeeRemove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 调岗 业务层
 *
 * @author 🍍
 * @date 2023/10/1
 */
@Service
public class EmployeeRemoveService {
    private final EmployeeRemoveMapper employeeRemoveMapper;

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeRemoveService(EmployeeRemoveMapper employeeRemoveMapper, EmployeeService employeeService) {
        this.employeeRemoveMapper = employeeRemoveMapper;
        this.employeeService = employeeService;
    }

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

}
