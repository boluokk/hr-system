package org.boluo.hr.mapper;

import org.boluo.hr.pojo.BaseEmployeeRemove;
import org.boluo.hr.pojo.EmployeeRemove;

import java.util.List;

/**
 * 调岗 数据层
 *
 * @author boluo
 */
public interface EmployeeRemoveMapper {
    /**
     * 删除调岗
     *
     * @param id 调岗id
     * @return 结果
     */
    int deleteByPrimaryKey(Integer id);


    /**
     * 新增调岗
     *
     * @param baseEmployeeRemove 调岗信息
     * @return 结果
     */
    int insertEmployeeRemove(BaseEmployeeRemove baseEmployeeRemove);


    /**
     * 通过调岗id 查询调岗
     *
     * @param id
     * @return 调岗信息
     */
    EmployeeRemove selectByPrimaryKey(Integer id);

    /**
     * 修改调岗
     *
     * @param baseEmployeeRemove 调岗信息
     * @return 结果
     */
    int updateByPrimaryKey(BaseEmployeeRemove baseEmployeeRemove);

    /**
     * 查询所有调岗信息
     *
     * @return 调岗集合
     */
    List<EmployeeRemove> selectAll();
}