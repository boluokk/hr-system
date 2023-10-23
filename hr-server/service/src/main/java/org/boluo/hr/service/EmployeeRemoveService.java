package org.boluo.hr.service;

import org.boluo.hr.mapper.EmployeeRemoveMapper;
import org.boluo.hr.pojo.EmployeeRemove;
import org.boluo.hr.pojo.EmployeeRemoveView;
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

    @Autowired
    public EmployeeRemoveService(EmployeeRemoveMapper employeeRemoveMapper) {
        this.employeeRemoveMapper = employeeRemoveMapper;
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
     * @param employeeRemove 调岗信息
     * @return 结果
     */
    public boolean update(EmployeeRemove employeeRemove) {
        return employeeRemoveMapper.updateByPrimaryKey(employeeRemove) == 1;
    }

    /**
     * 新增调岗
     *
     * @param employeeRemove 调岗信息
     * @return 结果
     */
    public boolean insert(EmployeeRemove employeeRemove) {
        return employeeRemoveMapper.insertEmployeeRemove(employeeRemove) == 1;
    }
}
