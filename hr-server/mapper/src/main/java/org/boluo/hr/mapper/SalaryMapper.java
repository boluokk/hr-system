package org.boluo.hr.mapper;

import org.apache.ibatis.annotations.Param;
import org.boluo.hr.pojo.InsertSalary;
import org.boluo.hr.pojo.Salary;
import org.boluo.hr.pojo.SalaryConfigView;
import org.boluo.hr.pojo.UploadSalary;

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
     * @param insertSalary 工资账套
     * @return 结果
     */
    int insertSalary(InsertSalary insertSalary);

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
     * @param uploadSalary 工资账套信息
     * @return 结果
     */
    int updateByPrimaryKey(UploadSalary uploadSalary);

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
     * @return 员工账套信息集合
     */
    List<SalaryConfigView> selectEmployeeAndSalary();

    /**
     * 通过员工工号查询
     *
     * @param workId 员工工号
     * @return 员工账套信息
     */
    SalaryConfigView selectEmployeeSalaryByWorkId(@Param("workId") String workId);

}