package org.boluo.hr.service;

import org.boluo.hr.mapper.OperatorLogMapper;
import org.boluo.hr.pojo.OperatorLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 日志记录 业务层
 *
 * @author 🍍
 * @date 2023/10/25
 */
@Service
public class OperatorLogService {

    private final OperatorLogMapper operatorLogMapper;

    @Autowired
    public OperatorLogService(OperatorLogMapper operatorLogMapper) {
        this.operatorLogMapper = operatorLogMapper;
    }

    /**
     * 新增
     *
     * @param operatorLog 日志记录
     * @return 结果
     */
    public boolean insertOperatorLog(OperatorLog operatorLog) {
        return operatorLogMapper.insertOperatorLog(operatorLog) == 1;
    }
}
