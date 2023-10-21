package org.boluo.hr.mapper;

import org.boluo.hr.pojo.BaseEmployeeTrain;
import org.boluo.hr.pojo.EmployeeTrain;

import java.util.List;

/**
 * 培训 数据层
 *
 * @author boluo
 */
public interface EmployeeTrainMapper {

    /**
     * 通过id 删除培训
     *
     * @param id
     * @return 结果
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增培训
     *
     * @param baseEmployeeTrain 培训信息
     * @return 结果
     */
    int insertEmployeeTrain(BaseEmployeeTrain baseEmployeeTrain);

    /**
     * 通过id 查询培训
     *
     * @param id 培训id
     * @return 培训信息
     */
    EmployeeTrain selectByPrimaryKey(Integer id);

    /**
     * 修改培训
     *
     * @param baseEmployeeTrain 培训信息
     * @return 结果
     */
    int updateByPrimaryKey(BaseEmployeeTrain baseEmployeeTrain);

    /**
     * 查询所有培新 包含员工信息
     *
     * @return 培训集合
     */
    List<EmployeeTrain> selectAll();

}