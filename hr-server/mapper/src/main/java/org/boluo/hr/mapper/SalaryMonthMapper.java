package org.boluo.hr.mapper;

import org.boluo.hr.pojo.SalaryMonth;
import java.util.List;

/**
 * 工资月末 数据层
 *
 * @author @1352955539(boluo)
 * @date 2021/3/25 - 22:21
 */
public interface SalaryMonthMapper {

    /**
     * 查询所有工资月末
     *
     * @return 工资月末集合
     */
    List<SalaryMonth> selectAll();

    List<SalaryMonth> selectByEmployeeWorkId(Integer employeeWorkId);
}
