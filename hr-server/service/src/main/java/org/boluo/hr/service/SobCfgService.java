package com.boluo.hr.service;

import org.boluo.hr.mapper.SalaryMapper;
import org.boluo.hr.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author @1352955539(boluo)
 * @date 2021/2/21 - 21:54
 */
@Service
public class SobCfgService {

    @Autowired
    SalaryMapper salaryMapper;

    public List<Employee> getEmpWithSalary(Integer num,Integer size) {
        if(num!=null && size!=null) {
            int realNum = size*(num-1);
            return salaryMapper.selectEmpAndSalary(realNum,size);
        }
        return null;
    }

    public int CountEmpWithSal() {
       return salaryMapper.CountSalaryWithEmp();
    }

    public void addSalWEmp(Integer eid,Integer salid) {
        salaryMapper.insertEmpWithSal(eid,salid);
    }
}
