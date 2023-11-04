package org.boluo.hr.mapper;

import org.boluo.hr.pojo.DataBaseTable;

import java.util.List;

/**
 * 数据库备份 数据层
 *
 * @author 🍍
 * @date 2023/11/02
 */
public interface DataBaseMapper {
    /**
     * 列出所有数据库表信息
     *
     * @return 数据库表信息集合
     */
    List<DataBaseTable> selectAllTablesInfo();

    /**
     * 清空表
     *
     * @param tableName 表名
     */
    void truncateTable(String tableName);

    /**
     * 列出需要清空的表
     *
     * @return 表名集合
     */
    List<String> selectTruncateTable();
}
