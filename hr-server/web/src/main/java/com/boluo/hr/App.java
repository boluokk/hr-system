package com.boluo.hr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author @1352955539(boluo)
 * @date 2021/1/25 - 16:35
 */
@SpringBootApplication
@MapperScan("com.boluo.hr.mapper")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
