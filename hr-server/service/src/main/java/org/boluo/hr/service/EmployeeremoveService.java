package org.boluo.hr.service;

import org.boluo.hr.mapper.EmployeeremoveMapper;
import org.boluo.hr.pojo.Employeeremove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 🍍
 * @date 2023/10/1
 */
@Service
public class EmployeeremoveService {
    private final EmployeeremoveMapper employeeremoveMapper;

    @Autowired
    public EmployeeremoveService(EmployeeremoveMapper employeeremoveMapper) {
        this.employeeremoveMapper = employeeremoveMapper;
    }

    public List<Employeeremove> selectAll() {
        List<Employeeremove> list = employeeremoveMapper.selectAll();
        List<Employeeremove> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i % 2 != 0) {
                Employeeremove emp;
                emp = list.get(i - 1);
                emp.setNewJoblevelName(list.get(i).getOldJoblevelName());
                emp.setNewDepartName(list.get(i).getOldDepartName());
                newList.add(emp);
            }
        }
        return newList;
    }
}
