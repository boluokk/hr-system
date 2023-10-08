package org.boluo.hr.service;

import org.boluo.hr.mapper.EmployeetrainMapper;
import org.boluo.hr.pojo.Employeetrain;
import org.boluo.hr.pojo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author 🍍
 * @date 2023/10/1
 */
@Service
public class EmployeetrainService {

    private final EmployeetrainMapper employeetrainMapper;

    @Autowired
    public EmployeetrainService(EmployeetrainMapper employeetrainMapper) {
        this.employeetrainMapper = employeetrainMapper;
    }

    public List<Employeetrain> selectAll() {
        return employeetrainMapper.selectAll();
    }

    public List<Employeetrain> selectAllWithEmpName() {
        return employeetrainMapper.selectAllWithEmpName();
    }

    public boolean updateOne(Employeetrain employeetrain) {
        return employeetrainMapper.updateByPrimaryKeySelective(employeetrain) == 1;
    }

    public boolean deleteEmpTrain(Integer id) {
        return employeetrainMapper.deleteByPrimaryKey(id) == 1;
    }

    public boolean insertOne(Employeetrain employeetrain) {
        return employeetrainMapper.insertSelective(employeetrain) == 1;
    }
}
