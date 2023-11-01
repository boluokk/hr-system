package org.boluo.hr;

import org.boluo.hr.mapper.HrRecordStatisticsMapper;
import org.boluo.hr.pojo.DepartmentStatistics;
import org.boluo.hr.pojo.Employee;
import org.boluo.hr.pojo.WrapHrRecordStatistics;
import org.boluo.hr.service.AllStatisticsService;
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

    @Autowired
    HrRecordStatisticsMapper hrRecordStatisticsMapper;

    @Autowired
    AllStatisticsService allStatisticsService;

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

    /**
     * 人事记录统计测试
     */
    @Test
    public void getHrRecordData() {
        List<WrapHrRecordStatistics> x = hrRecordStatisticsMapper.selectAll(3);
        System.out.println(x.size());
        x.forEach(System.out::println);
    }

    /**
     * 部门机构测试
     */
    @Test
    public void getDepartmentStruct() {
        DepartmentStatistics departmentStatistics = allStatisticsService.selectDepartmentStruct();
        System.out.println(departmentStatistics);
    }
}
