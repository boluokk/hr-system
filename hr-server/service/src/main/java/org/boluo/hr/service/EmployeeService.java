package org.boluo.hr.service;

import org.boluo.hr.mapper.EmployeeMapper;
import org.boluo.hr.pojo.Employee;
import org.boluo.hr.pojo.MailConstans;
import org.boluo.hr.pojo.Nation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
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

    public List<Employee> selectAll() {
        return employeeMapper.selectAll();
    }

    public int selectTotal() {
        return employeeMapper.selectCount();
    }

    public List<Employee> selectByEmpName(String empName) {
        return employeeMapper.selectByEmpName(empName);
    }

    /**
     * 会发送邮件, 利用Rabbit防止发送失败
     */
    public boolean insertOne(Employee employee) {
        int i = employeeMapper.insertSelective(employee);
        if (i == 1) {
            Employee emp = employeeMapper.selectByEmpId(employee.getId());
            rabbitTemplate.convertAndSend(MailConstans.MAIL_QUEUE_NAME, emp);
        }
        return i == 1;
    }

    public int selectMaxWorkId() {
        return employeeMapper.selectMaxWorkId() + 1;
    }

    public boolean update(Employee employee) {
        return employeeMapper.updateByPrimaryKeySelective(employee) == 1;
    }

    public boolean delete(Integer id) {
        return employeeMapper.deleteByPrimaryKey(id) == 1;
    }

    public int deleteByMany(Integer[] ids) {
        return employeeMapper.deleteByManyId(ids);
    }

    public List<Nation> selectNations() {
        return employeeMapper.selectNations();
    }

    public int insertMany(List<Employee> employees) {
        return employeeMapper.insertMany(employees);
    }

    public List<Employee> selectByPageAndEmployee(Employee employee) {
        return employeeMapper.selectByPageAndEmployee(employee);
    }

    public int selectByEmployeeCount(Employee employee) {
        return employeeMapper.selectByEmployeeCount(employee);
    }
}
