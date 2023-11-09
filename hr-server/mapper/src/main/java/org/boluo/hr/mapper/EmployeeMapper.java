package org.boluo.hr.mapper;

import org.apache.ibatis.annotations.Param;
import org.boluo.hr.pojo.Employee;
import org.boluo.hr.pojo.InsertEmployee;
import org.boluo.hr.pojo.Nation;
import org.boluo.hr.pojo.UploadEmployee;

import java.util.List;

/**
 * 员工 数据层
 *
 * @author boluo
 */
public interface EmployeeMapper {

    /**
     * 通过员工id 删除员工
     *
     * @param id 员工id
     * @return 结果
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增员工
     *
     * @param insertEmployee 员工信息
     * @return 结果
     */
    int insertEmployee(InsertEmployee insertEmployee);

    /**
     * 通过员工id 查询员工
     *
     * @param id 员工id
     * @return 员工信息
     */
    Employee selectByPrimaryKey(Integer id);

    /**
     * 修改员工
     *
     * @param uploadEmployee 员工信息
     * @return 结果
     */
    int updateByPrimaryKey(UploadEmployee uploadEmployee);

    /**
     * 查询所有员工
     *
     * @return 员工集合
     */
    List<Employee> selectAll();

    /**
     * 查询员工数
     *
     * @return 员工数
     */
    int selectCount();

    /**
     * 通过员工名字 查询员工
     *
     * @param empName 员工名字
     * @return 员工集合
     */
    List<Employee> selectByEmpName(@Param("empName") String empName);

    /**
     * 批量查询员工
     *
     * @param ids 员工id数组
     * @return 结果
     */
    int batchDeleteEmployee(@Param("ids") Integer[] ids);

    /**
     * 查询所有民族
     *
     * @return 民族集合
     */
    List<Nation> selectNations();

    /**
     * 批量插入员工
     *
     * @param employees 员工信息
     * @return 结果
     */
    int batchInsertEmployee(@Param("list") List<Employee> employees);

    /**
     * 通过员工信息 查询员工
     *
     * @param uploadEmployee 员工信息
     * @return 员工集合
     */
    List<Employee> selectByEmployee(@Param("employee") UploadEmployee uploadEmployee);

    /**
     * 满足员工信息字段的员工个数
     *
     * @param employee 员工信息
     * @return 员工个数
     */
    int selectByEmployeeCount(@Param("employee") Employee employee);

    /**
     * 通过员工id 查询增强员工信息
     *
     * @param id 员工id
     * @return 增强员工信息
     */
    Employee selectEnhanceEmployeeByEmployeeId(@Param("id") Integer id);


    /**
     * 通过员工号 查询员工信息
     *
     * @param workId 员工号
     * @return 员工信息
     */
    UploadEmployee selectByEmployeeByWorkId(@Param("workId") String workId);
}