package org.boluo.hr.service;

import cn.hutool.core.io.FileUtil;
import org.boluo.hr.mapper.DataBaseMapper;
import org.boluo.hr.pojo.DataBaseTable;
import org.boluo.hr.util.CustomFileUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库备份 业务层
 *
 * @author 🍍
 * @date 2023/11/02
 */
@Service
public class DataBaseService {
    @Resource
    private DataBaseMapper dataBaseMapper;
    @Resource
    private CustomFileUtil customFileUtil;

    /**
     * 获取所有表信息
     *
     * @return 数据表信息集合
     */
    public List<DataBaseTable> selectAllTables() {
        return dataBaseMapper.selectAllTablesInfo();
    }

    /**
     * 备份数据库
     */
    public ResponseEntity<byte[]> backup() {
        try {
            String sqlFileName = "/backup.sql";
            Process dumpSql = Runtime.getRuntime().exec(customFileUtil.getDumpDatabaseCommand(sqlFileName));
            if (dumpSql.waitFor() == 0) {
                return customFileUtil.getDatabaseBackupFile(sqlFileName);
            } else {
                return ResponseEntity.status(500).body("数据库备份失败".getBytes());
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("文件异常".getBytes());
        }
    }

    /**
     * 清空数据库
     *
     * @return 结果
     */
    public boolean truncateTable() {
        List<String> truncateTable = dataBaseMapper.selectTruncateTable();
        if (!truncateTable.isEmpty()) {
            try {
                for (String table : truncateTable) {
                    dataBaseMapper.truncateTable(table);
                }
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取备份版本
     */
    public List<String> getBackupVersion() {
        List<String> fileName = new ArrayList<>();
        File[] ls = FileUtil.ls(customFileUtil.getEnhanceServerBackupFilePath("/history"));
        for (File file : ls) {
            fileName.add(file.getName());
        }
        return fileName;
    }

    public boolean restoreByVersion(String version) {
        String pre = "/history/";
        return customFileUtil.getRestoreDatabaseStatus(pre + version);
    }

    public boolean restoreByUpload(String fileName) {
        String pre = "/upload/";
        return customFileUtil.getRestoreDatabaseStatus(pre + fileName);
    }
}
