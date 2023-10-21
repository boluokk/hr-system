package org.boluo.hr.service;

import org.boluo.hr.mapper.EmployeeRemoveMapper;
import org.boluo.hr.pojo.BaseEmployeeRemove;
import org.boluo.hr.pojo.EmployeeRemove;
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
    public List<EmployeeRemove> selectAll() {
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
     * @param baseEmployeeRemove 调岗信息
     * @return 结果
     */
    public boolean update(BaseEmployeeRemove baseEmployeeRemove) {
        return employeeRemoveMapper.updateByPrimaryKey(baseEmployeeRemove) == 1;
    }

    /**
     * 新增调岗
     *
     * @param baseEmployeeRemove 调岗信息
     * @return 结果
     */
    public boolean insert(BaseEmployeeRemove baseEmployeeRemove) {
        return employeeRemoveMapper.insertEmployeeRemove(baseEmployeeRemove) == 1;
    }
}
