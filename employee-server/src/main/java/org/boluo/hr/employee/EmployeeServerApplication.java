package org.boluo.hr.employee;

import org.bluo.hr.service.api.HrServiceClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author boluo
 */
@SpringBootApplication
@EnableFeignClients(basePackageClasses = HrServiceClient.class)
public class EmployeeServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmployeeServerApplication.class, args);
    }

}
