package org.boluo.hr.service;

import org.boluo.hr.mapper.EmployeeSalaryMergeMapper;
import org.boluo.hr.util.CheckUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 员工账套关系 业务层
 *
 * @author boluo
 * @date 2023/11/18
 */
@Service
public class EmployeeSalaryMergeService {

    @Resource
    private EmployeeSalaryMergeMapper employeeSalaryMergeMapper;

    /**
     * 删除员工账套关系
     *
     * @param employeeId 员工id
     * @return 结果
     */
    public boolean findAfterDelete(Integer employeeId) {
        if (CheckUtil.isNull(employeeSalaryMergeMapper.selectByEmployeeId(employeeId))) {
            return true;
        }
        if (employeeSalaryMergeMapper.deleteByEmployeeId(employeeId) == 1) {
            return true;
        }
        return false;
    }
}
