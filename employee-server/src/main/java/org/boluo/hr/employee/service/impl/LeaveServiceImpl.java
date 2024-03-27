package org.boluo.hr.employee.service.impl;

import org.boluo.hr.employee.mapper.LeaveMapper;
import org.boluo.hr.employee.pojo.Leave;
import org.boluo.hr.employee.service.LeaveService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author boluo
 * @version 1.0.0
 * @date 2024/02/09
 */
@Service
public class LeaveServiceImpl implements LeaveService {

    @Resource
    private LeaveMapper leaveMapper;

    @Override
    public List<Leave> findAll(int employeeId) {
        return leaveMapper.findAll(employeeId);
    }

    @Override
    public boolean add(Leave leave) {
        return leaveMapper.insert(leave) == 1;
    }

    @Override
    public boolean delete(int id) {
        return leaveMapper.delete(id) == 1;
    }
}
