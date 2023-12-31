package org.boluo.hr.service;

import org.boluo.hr.mapper.EmployeeTrainMapper;
import org.boluo.hr.pojo.EmployeeTrainView;
import org.boluo.hr.pojo.InsertEmployeeTrain;
import org.boluo.hr.pojo.UploadEmployeeTrain;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 培训 业务层
 *
 * @author 🍍
 * @date 2023/10/1
 */
@Service
public class EmployeeTrainService {

    @Resource
    private EmployeeTrainMapper employeeTrainMapper;

    /**
     * 所有培训信息
     *
     * @return 培训集合
     */
    public List<EmployeeTrainView> selectAll() {
        return employeeTrainMapper.selectAll();
    }

    /**
     * 修改培训
     *
     * @param uploadEmployeeTrain 培训信息
     * @return 结果
     */
    public boolean update(UploadEmployeeTrain uploadEmployeeTrain) {
        return employeeTrainMapper.updateByPrimaryKey(uploadEmployeeTrain) == 1;
    }


    /**
     * 删除培训
     *
     * @param id 培训id
     * @return 结果
     */
    public boolean delete(Integer id) {
        return employeeTrainMapper.deleteByPrimaryKey(id) == 1;
    }


    /**
     * 新增培训
     *
     * @param insertEmployeeTrain 培训信息
     * @return 结果
     */
    public boolean insert(InsertEmployeeTrain insertEmployeeTrain) {
        return employeeTrainMapper.insertEmployeeTrain(insertEmployeeTrain) == 1;
    }
}
