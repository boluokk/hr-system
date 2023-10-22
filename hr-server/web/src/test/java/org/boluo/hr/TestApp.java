package org.boluo.hr;

import org.boluo.hr.pojo.Employee;
import org.boluo.hr.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author 🍍
 * @date 2023/10/21
 */

@SpringBootTest
public class TestApp {

    @Autowired
    EmployeeService employeeService;

    /**
     * 修改workId, 让workId等于主键值
     */
    @Test
    public void modifyWorkIdTest() {
        List<Employee> employees = employeeService.selectAll();
        for (Employee item : employees) {
            employeeService.update(new Employee().setId(item.getId()).setWorkId(String.format("%08d", item.getId())));
        }
    }
}
