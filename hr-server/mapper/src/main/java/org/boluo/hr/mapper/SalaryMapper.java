package org.boluo.hr.mapper;

import org.apache.ibatis.annotations.Param;
import org.boluo.hr.pojo.Employee;
import org.boluo.hr.pojo.Salary;
import org.boluo.hr.pojo.SalaryConfigView;

import java.util.List;

/**
 * 工资账套 数据层
 *
 * @author boluo
 */
public interface SalaryMapper {

    /**
     * 删除工资账套
     *
     * @param id 工资账套id
     * @return 结果
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增工资账套
     *
     * @param salary 工资账套
     * @return 结果
     */
    int insertSalary(Salary salary);

    /**
     * 查询工资账套
     *
     * @param id 工资账套id
     * @return 工资账套
     */
    Salary selectByPrimaryKey(Integer id);

    /**
     * 修改工资账套
     *
     * @param salary 工资账套信息
     * @return 结果
     */
    int updateByPrimaryKey(Salary salary);

    /**
     * 查询所有工资账套
     *
     * @return 工资账套集合
     */
    List<Salary> selectAll();

    /**
     * 批量删除
     *
     * @param ids 工资账套id数组
     * @return 结果
     */
    int deleteMany(@Param("ids") Integer[] ids);

    /**
     * 所有有账套的员工
     *
     * @return 有账套员工个数
     */
    int countSalaryWithEmployee();

    /**
     * 查询带员工信息的工资账套信息
     *
     * @return 员工信息集合
     */
    List<SalaryConfigView> selectEmployeeAndSalary();
}