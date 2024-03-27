package org.boluo.hr.employee;

import org.bluo.hr.service.api.HrServiceClient;
import org.bluo.hr.service.api.pojo.SalaryTableSearch;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest
class EmployeeServerApplicationTests {

    @Resource
    HrServiceClient hrServiceClient;

    @Test
    void contextLoads() {
        SalaryTableSearch salaryTableSearch = new SalaryTableSearch();
        salaryTableSearch.setDate(new Date());
        System.out.println(hrServiceClient.findPage(1, 10, salaryTableSearch).getObj());
    }

}
