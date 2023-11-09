package org.boluo.hr.service;

import cn.hutool.json.JSONUtil;
import org.boluo.hr.exception.BusinessException;
import org.boluo.hr.mapper.EmployeeMapper;
import org.boluo.hr.pojo.*;
import org.boluo.hr.util.CheckUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * @param insertEmployee 员工
     * @return 结果
     * todo 未保证发送到rabbitMQ
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean insertOne(InsertEmployee insertEmployee) {
        if (employeeMapper.insertEmployee(insertEmployee) == 0) {
            return false;
        }
        // 修改workId
        UploadEmployee uploadEmployee = new UploadEmployee();
        uploadEmployee.setId(insertEmployee.getId());
        uploadEmployee.setWorkId(String.format("%08d", insertEmployee.getId()));
        if (employeeMapper.updateByPrimaryKey(uploadEmployee) == 0) {
            throw new BusinessException("更新员工工号失败");
        }
        Employee enhanceEmployee = employeeMapper.selectEnhanceEmployeeByEmployeeId(insertEmployee.getId());
        if (CheckUtil.isNull(enhanceEmployee)) {
            throw new BusinessException("查询员工数据失败");
        }
        rabbitTemplate.convertAndSend(MailConstants.MAIL_QUEUE_NAME, JSONUtil.toJsonStr(enhanceEmployee));
        return true;
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
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<Employee> employees) {
        return employeeMapper.batchInsertEmployee(employees);
    }

    /**
     * 返回符合员工信息条件的员工
     *
     * @param uploadEmployee 员工信息
     * @return 员工集合
     */
    public List<Employee> selectByPageAndEmployee(UploadEmployee uploadEmployee) {
        return employeeMapper.selectByEmployee(uploadEmployee);
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


    public UploadEmployee selectEmployeeByWorkId(String workId) {
        return employeeMapper.selectByEmployeeByWorkId(workId);
    }

}
