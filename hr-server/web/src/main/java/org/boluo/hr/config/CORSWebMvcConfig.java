package org.boluo.hr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;

/**
 * @author 🍍
 * @date 2023/10/1
 */
@Configuration
public class CORSWebMvcConfig implements WebMvcConfigurer {
    public CorsConfiguration buildConfig(){
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*"); //允许任何域名
        config.addAllowedHeader("*"); //允许任何头
        config.addAllowedMethod("*"); //允许任何方法
        config.addAllowedOrigin("*");
        config.setMaxAge(Duration.ofDays(30));   //设置30天之内不在发送OPTIONS预请求
        return config;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }
}
