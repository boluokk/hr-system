package com.boluo.hr.service;

import com.boluo.hr.mapper.EmployeeMapper;
import com.boluo.hr.pojo.Employee;
import com.boluo.hr.pojo.MailConstans;
import com.boluo.hr.pojo.Nation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author @1352955539(boluo)
 * @date 2021/2/10 - 22:58
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    RabbitTemplate rabbitTemplate;


    public List<Employee> selectAllEmp(Integer pageNum,Integer pageSize) {
        return employeeMapper.selectAllByPage(pageNum,pageSize);
    }

    public List<Employee> selectAllByNothing() {
        return employeeMapper.selectAllByPage(null,null);
    }

    public int selectTotal() {
        return employeeMapper.selectCount();
    }

    public List<Employee> selectByEmpName(String empName) {
        return employeeMapper.selectByEmpName(empName);
    }

    public int insertEmpOfOne(Employee employee) {
        int i = employeeMapper.insertSelective(employee);
        if(i==1) {
            Employee emp = employeeMapper.selectByEmpID(employee.getId());
            rabbitTemplate.convertAndSend(MailConstans.MAIL_QUEUE_NAME,emp);
        }
        return i;
    }

    public int selectMaxWorkID() {
        Integer i = employeeMapper.selectMaxWorkID();
        return i+1;
    }

    public int upDateEmp(Employee employee) {
        return employeeMapper.updateByPrimaryKeySelective(employee);
    }

    public int deltebyOne(Integer id) {
        return employeeMapper.deleteByPrimaryKey(id);
    }

    public int deleteByMany(Integer[] ids) {
        return employeeMapper.deleteByManyID(ids);
    }

    public List<Nation> testNation() {
        return employeeMapper.selectByNation();
    }

    public int insertMany(List<Employee> employees) {
        return employeeMapper.insertMany(employees);
    }

    public List<Employee> TipSerchByEmp(Integer pageNum, Integer pageSize, Employee employee) {
        return employeeMapper.selectByTipSerch(pageNum,pageSize,employee);
    }

    public int TipCount(Employee employee) {
        return employeeMapper.selectByTipCount(employee);
    }
}
