package org.boluo.hr.service;

import org.boluo.hr.mapper.EmployeeTrainMapper;
import org.boluo.hr.pojo.EmployeeTrain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 培训 业务层
 *
 * @author 🍍
 * @date 2023/10/1
 */
@Service
public class EmployeeTrainService {

    private final EmployeeTrainMapper employeeTrainMapper;

    @Autowired
    public EmployeeTrainService(EmployeeTrainMapper employeeTrainMapper) {
        this.employeeTrainMapper = employeeTrainMapper;
    }


    /**
     * 所有培训信息
     *
     * @return 培训集合
     */
    public List<EmployeeTrain> selectAll() {
        return employeeTrainMapper.selectAll();
    }

    /**
     * 通过员工名称 查询培训
     *
     * @return 培训集合
     */
    public List<EmployeeTrain> selectAllWithEmployeeName() {
        return employeeTrainMapper.selectAllWithEmployeeName();
    }

    /**
     * 修改培训
     *
     * @param employeetrain 培新信息
     * @return 结果
     */
    public boolean update(EmployeeTrain employeetrain) {
        return employeeTrainMapper.updateByPrimaryKey(employeetrain) == 1;
    }


    /**
     * 删除培训
     *
     * @param id 培新id
     * @return 结果
     */
    public boolean delete(Integer id) {
        return employeeTrainMapper.deleteByPrimaryKey(id) == 1;
    }


    /**
     * 新增培训
     *
     * @param employeetrain 培训信息
     * @return 结果
     */
    public boolean insert(EmployeeTrain employeetrain) {
        return employeeTrainMapper.insertEmployeeTrain(employeetrain) == 1;
    }
}
