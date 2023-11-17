package org.boluo.hr;

import org.bluo.common.redis.config.EnableRedisConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author boluo
 * @date 2023/10/1
 */
@MapperScan("org.boluo.hr.mapper")
@EnableScheduling
@SpringBootApplication
@EnableRedisConfig
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
