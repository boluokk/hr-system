package org.boluo.hr.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

/**
 * 自定义配置
 *
 * @author 🍍
 * @date 2023/11/02
 */
@Data
@Repository
public class CustomConfig {
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.name}")
    private String databaseName;
    @Value("${custom.config.docker_database_path}")
    private String dockerBackupFilePath;
    @Value("${custom.config.server_database_path}")
    private String serverBackupFilePath;
    @Value("${custom.config.docker_name}")
    private String dockerName;
    @Value("${custom.config.backup_expire}")
    private long backupExpire;
}
