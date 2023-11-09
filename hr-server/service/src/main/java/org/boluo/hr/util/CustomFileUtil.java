package org.boluo.hr.util;

import lombok.extern.slf4j.Slf4j;
import org.boluo.hr.pojo.CustomConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 获取一些属性的工具类
 *
 * @author 🍍
 * @date 2023/11/02
 */
@Component
@Slf4j
public class CustomFileUtil {
    private final CustomConfig customConfig;

    @Autowired
    public CustomFileUtil(CustomConfig customConfig) {
        this.customConfig = customConfig;
    }

    /**
     * 获取数据库备份命令
     */
    public String getDumpDatabaseCommand(String sqlFileName) {
        return String.format("docker exec %s bash -c \"mysqldump -u%s -p%s %s > %s%s\"",
                customConfig.getDockerName(), customConfig.getUsername(),
                customConfig.getPassword(), customConfig.getDatabaseName(),
                customConfig.getDockerBackupFilePath(), sqlFileName);
    }

    /**
     * 获取数据库恢复命令
     */
    public String getRestoreDatabaseCommand(String sqlFileName) {
        return String.format("docker exec %s bash -c \"mysql -u%s -p%s %s < %s%s\"",
                customConfig.getDockerName(), customConfig.getUsername(),
                customConfig.getPassword(), customConfig.getDatabaseName(),
                customConfig.getDockerBackupFilePath(), sqlFileName);
    }

    /**
     * 获取服务器备份文件路径
     */
    public String getEnhanceServerBackupFilePath(String sqlFileName) {
        return customConfig.getServerBackupFilePath() + sqlFileName;
    }


    /**
     * 获取服务器数据库备份文件
     */
    public ResponseEntity<byte[]> getDatabaseBackupFile(String sqlFileName) throws IOException {
        File backupFile = new File(getEnhanceServerBackupFilePath(sqlFileName));
        byte[] fileContent = Files.readAllBytes(backupFile.toPath());
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=backup.sql")
                .body(fileContent);
    }

    /**
     * 数据库恢复
     */
    public boolean getRestoreDatabaseStatus(String sqlFileName) {
        String restoreDatabaseCommand = getRestoreDatabaseCommand(sqlFileName);
        try {
            return Runtime.getRuntime().exec(restoreDatabaseCommand).waitFor() == 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取备份过期时间
     */
    public long getBackupExpire() {
        return System.currentTimeMillis() - customConfig.getBackupExpire() * 1000;
    }

    /**
     * 获取当前格式化时间
     */
    public String getCurrentFormatTime(String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    public boolean saveFile(String path, MultipartFile file) {
        try {
            String pathName = file.getOriginalFilename();
            file.transferTo(new File(path + pathName));
            return true;
        } catch (IOException e) {
            log.warn("文件保存异常");
            return false;
        }
    }
}
