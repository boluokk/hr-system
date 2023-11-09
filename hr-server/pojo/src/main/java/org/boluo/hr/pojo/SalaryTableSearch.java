package org.boluo.hr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

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
    @Length(min = 8, max = 8, message = "员工号长度必须为8")
    private String workId;
    /**
     * 工资时间
     */
    private Date date;
}
