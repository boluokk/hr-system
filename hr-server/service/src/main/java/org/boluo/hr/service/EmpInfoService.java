package org.boluo.hr.service;

import org.boluo.hr.mapper.EmployeeMapper;
import org.boluo.hr.pojo.Employee;
import org.boluo.hr.pojo.UploadEmployee;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 员工信息 业务层
 *
 * @author 🍍
 * @date 2023/10/1
 */
@Service
public class EmpInfoService {
    @Resource
    private EmployeeMapper employeeMapper;

    /**
     * 通过员工名 查询员工
     *
     * @param empName 员工名
     * @return 员工集合
     */
    public List<Employee> selectByEmpName(String empName) {
        return employeeMapper.selectByEmpName(empName);
    }

    /**
     * 修改员工
     *
     * @param uploadEmployee 员工信息
     * @return 结果
     */
    public boolean update(UploadEmployee uploadEmployee) {
        return employeeMapper.updateByPrimaryKey(uploadEmployee) == 1;
    }

    /**
     * 删除员工
     *
     * @param id 员工id
     * @return 结果
     */
    public boolean delete(Integer id) {
        return employeeMapper.deleteByPrimaryKey(id) == 1;
    }


}
