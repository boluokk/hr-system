package org.boluo.hr.service;

import org.boluo.hr.mapper.EmployeeMapper;
import org.boluo.hr.pojo.Employee;
import org.boluo.hr.pojo.MailConstants;
import org.boluo.hr.pojo.Nation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工 业务层
 *
 * @author 🍍
 * @date 2023/10/1
 */
@Service
public class EmployeeService {

    private final EmployeeMapper employeeMapper;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public EmployeeService(EmployeeMapper employeeMapper, RabbitTemplate rabbitTemplate) {
        this.employeeMapper = employeeMapper;
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * 返回所有员工的信息
     *
     * @return 员工集合
     */
    public List<Employee> selectAll() {
        return employeeMapper.selectAll();
    }

    /**
     * 返回员工个数
     *
     * @return 员工个数
     */
    public int selectTotal() {
        return employeeMapper.selectCount();
    }

    /**
     * 通过员工名 返回员工
     *
     * @param empName 员工名
     * @return 员工集合
     */
    public List<Employee> selectByEmpName(String empName) {
        return employeeMapper.selectByEmpName(empName);
    }

    /**
     * 发送邮件, 利用Rabbit防止发送失败
     *
     * @param employee 员工
     * @return 结果
     * todo 未保证发送到rabbitMQ
     */
    public boolean insertOne(Employee employee) {
        int i = employeeMapper.insertEmployee(employee);
        if (i == 1) {
            Employee emp = employeeMapper.selectEnhanceEmployeeByEmployeeId(employee.getId());
            rabbitTemplate.convertAndSend(MailConstants.MAIL_QUEUE_NAME, emp);
        }
        return i == 1;
    }

    /**
     * 返回员工最大id + 1
     *
     * @return 员工最大id + 1
     */
    public int selectMaxByWorkId() {
        return employeeMapper.selectMaxByWorkId() + 1;
    }

    /**
     *
     * @return
     */
    public int selectMinByWorkId(String workId) {
        return employeeMapper.selectMinByWorkId(workId);
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

    /**
     * 批量删除员工
     *
     * @param ids 员工id数组
     * @return 结果
     */
    public int batchDelete(Integer[] ids) {
        return employeeMapper.batchDeleteEmployee(ids);
    }

    /**
     * 返回民族
     *
     * @return 民族集合
     */
    @Deprecated
    public List<Nation> selectNations() {
        return employeeMapper.selectNations();
    }

    /**
     * 批量插入员工
     *
     * @param employees 员工集合
     * @return 结果
     */
    public int batchInsert(List<Employee> employees) {
        return employeeMapper.batchInsertEmployee(employees);
    }

    /**
     * 返回符合员工信息条件的员工
     *
     * @param employee 员工信息
     * @return 员工集合
     */
    public List<Employee> selectByPageAndEmployee(Employee employee) {
        return employeeMapper.selectByEmployee(employee);
    }

    /**
     * 符合员工信息个数
     *
     * @param employee 员工信息
     * @return 员工个数
     */
    public int selectByEmployeeCount(Employee employee) {
        return employeeMapper.selectByEmployeeCount(employee);
    }


}
