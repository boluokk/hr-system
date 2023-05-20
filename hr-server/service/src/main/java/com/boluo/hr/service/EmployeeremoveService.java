package com.boluo.hr.service;

import com.boluo.hr.mapper.EmployeeremoveMapper;
import com.boluo.hr.pojo.Employeeremove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author @1352955539(boluo)
 * @date 2021/2/28 - 21:58
 */
@Service
public class EmployeeremoveService {
    @Autowired
    EmployeeremoveMapper employeeremoveMapper;

    public List<Employeeremove> getAll() {
        List<Employeeremove> list = employeeremoveMapper.selectAll();
        List<Employeeremove> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if(i%2!=0) {
                Employeeremove emp = new Employeeremove();
                emp=list.get(i-1);
                emp.setNewJoblevelName(list.get(i).getOldJoblevelName());
                emp.setNewDepartName(list.get(i).getOldDepartName());
                newList.add(emp);
            }
        }
        return newList;
    }
}
