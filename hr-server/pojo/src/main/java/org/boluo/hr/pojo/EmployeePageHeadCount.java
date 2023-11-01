package org.boluo.hr.pojo;

import lombok.Data;

/**
 * 操作员(人事)头部数据 实体
 *
 * @author 🍍
 * @date 2023/10/29
 */

@Data
public class EmployeePageHeadCount {
    /**
     * 在线个数
     */
    private Integer onlineCount;
    /**
     * 操作员个数
     */
    private Integer operatorCount;
    /**
     * 操作次数
     */
    private Integer operateCount;
}
