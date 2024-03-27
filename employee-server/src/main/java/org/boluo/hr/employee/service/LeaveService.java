package org.boluo.hr.employee.service;

import org.boluo.hr.employee.pojo.Leave;

import java.util.List;

/**
 * @author boluo
 * @date 2024/02/09
 */
public interface LeaveService {
    List<Leave> findAll(int employeeId);

    boolean add(Leave leave);

    boolean delete(int id);
}
