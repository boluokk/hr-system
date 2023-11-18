package org.boluo.hr.service;

import org.boluo.hr.mapper.OperatorLogMapper;
import org.boluo.hr.pojo.OperatorHeaderCount;
import org.boluo.hr.pojo.OperatorLog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 日志记录 业务层
 *
 * @author 🍍
 * @date 2023/10/25
 */
@Service
public class OperatorLogService {

    @Resource
    private OperatorLogMapper operatorLogMapper;

    /**
     * 新增
     *
     * @param operatorLog 日志记录
     * @return 结果
     */
    public boolean insertOperatorLog(OperatorLog operatorLog) {
        return operatorLogMapper.insertOperatorLog(operatorLog) == 1;
    }

    /**
     * 查询所有操作日志记录
     *
     * @return 操作日志记录集合
     */
    public List<OperatorLog> selectAllOperatorLog() {
        return operatorLogMapper.selectAllOperatorLog();
    }


    /**
     * 查询操作日志页头数据
     *
     * @return 操作日志页头
     */
    public OperatorHeaderCount selectOperatorHeaderCount() {
        return operatorLogMapper.selectOperatorHeaderCount();
    }
}
