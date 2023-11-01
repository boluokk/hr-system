package org.boluo.hr.pojo;

import lombok.Data;

import java.util.List;

/**
 * 部门结构统计 实体
 *
 * @author 🍍
 * @date 2023/11/01
 */
@Data
public class DepartmentStatistics {
    private String name;
    private List<DepartmentStatistics> children;
}
