package com.boluo.hr.service;

import org.boluo.hr.mapper.EmployeetrainMapper;
import org.boluo.hr.pojo.Employeetrain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author @1352955539(boluo)
 * @date 2021/2/28 - 21:36
 */
@Service
public class EmployeetrainService {

    @Autowired
    EmployeetrainMapper employeetrainMapper;

    public List<Employeetrain> getAll() {
        return employeetrainMapper.selectAll();
    }

    public List<Employeetrain> getAllWithEmpName() {
        return employeetrainMapper.selectAllWithEmpName();
    }

    public Integer updateEmpt(Employeetrain employeetrain) {
        return employeetrainMapper.updateByPrimaryKeySelective(employeetrain);
    }

    public Integer deleteEmpTrain(Integer id) {
        return employeetrainMapper.deleteByPrimaryKey(id);
    }

    public Integer addEmpTrain(Employeetrain employeetrain) {
        return  employeetrainMapper.insertSelective(employeetrain);
    }
}
