package com.boluo.hr.service;

import com.boluo.hr.mapper.EmployeeMapper;
import com.boluo.hr.mapper.EmployeeecMapper;
import com.boluo.hr.pojo.Employeeec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author @1352955539(boluo)
 * @date 2021/2/28 - 21:29
 */
@Service
public class EmployeecService {

    @Autowired
    EmployeeecMapper employeeecMapper;

    public List<Employeeec> getAll() {
        return employeeecMapper.selectAll();
    }

    public int update(Employeeec employeeec) {
        return employeeecMapper.updateByPrimaryKeySelective(employeeec);
    }

    public int delete(Integer id) {
        return employeeecMapper.deleteByPrimaryKey(id);
    }

    public int addEmpc(Employeeec employeeec) {
        employeeec.setEcdate(new Date());
        System.out.println(employeeec);
        Integer i = employeeecMapper.selectEid(employeeec.getWorkId());
        if(i!=null) {
            employeeec.setEid(i);
            return employeeecMapper.insertSelective(employeeec);
        } else {
            return 0;
        }
    }
}
