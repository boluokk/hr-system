package org.boluo.hr.pojo;

import lombok.Data;

/**
 * 数据库表 实体
 *
 * @author 🍍
 * @date 2023/11/02
 */
@Data
public class DataBaseTable {
    /**
     * 表名
     */
    private String tableName;
    /**
     * 表大小 (单位MB)
     */
    private Float tableSize;
    /**
     * 表行数
     */
    private Integer tableRows;
}
