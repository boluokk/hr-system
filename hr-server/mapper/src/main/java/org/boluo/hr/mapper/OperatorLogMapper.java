package org.boluo.hr.mapper;


import org.boluo.hr.pojo.OperatorHeaderCount;
import org.boluo.hr.pojo.OperatorLog;

import java.util.List;

/**
 * 操作日志 数据层
 *
 * @author boluo
 */
public interface OperatorLogMapper {

    /**
     * 删除操作日志
     *
     * @param id 操作日志id
     * @return 结果
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增操作日志
     *
     * @param operatorLog 操作日志信息
     * @return 结果
     */
    int insertOperatorLog(OperatorLog operatorLog);

    /**
     * 查询操作日志
     *
     * @param id 操作日志id
     * @return 操作日志信息
     */
    OperatorLog selectByPrimaryKey(Integer id);

    /**
     * 修改操作日志
     *
     * @param operatorLog 操作日志信息
     * @return 结果
     */
    int updateByPrimaryKey(OperatorLog operatorLog);

    /**
     * 查询所有操作日志
     *
     * @return 操作日志集合
     */
    List<OperatorLog> selectAllOperatorLog();

    /**
     * 查询操作日志页头
     *
     * @return 操作日志页头
     */
    OperatorHeaderCount selectOperatorHeaderCount();
}