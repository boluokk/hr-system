package org.boluo.hr.service;

import org.boluo.hr.mapper.EmployeeMapper;
import org.boluo.hr.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工信息 业务层
 *
 * @author 🍍
 * @date 2023/10/1
 */
@Service
public class EmpInfoService {

    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmpInfoService(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

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
     * @param employee 员工信息
     * @return 结果
     */
    public boolean update(Employee employee) {
        return employeeMapper.updateByPrimaryKey(employee) == 1;
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
