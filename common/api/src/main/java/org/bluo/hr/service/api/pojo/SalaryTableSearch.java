package org.bluo.hr.service.api.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 工资表搜索 实体
 *
 * @author 🍍
 * @date 2023/10/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SalaryTableSearch {
    /**
     * 员工号
     */
    private String workId;
    /**
     * 工资时间
     */
    private Date date;
}
