package org.boluo.hr.service;

import org.boluo.hr.mapper.PoliticsStatusMapper;
import org.boluo.hr.pojo.PoliticsStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 政治面貌 业务层
 *
 * @author 🍍
 * @date 2023/10/1
 */
@Service
public class PoliticsStatusService {

    private final PoliticsStatusMapper politicsStatusMapper;

    @Autowired
    public PoliticsStatusService(PoliticsStatusMapper politicsStatusMapper) {
        this.politicsStatusMapper = politicsStatusMapper;
    }

    /**
     * 返回所有政治面貌信息
     *
     * @return 政治面貌集合
     */
    public List<PoliticsStatus> selectAllPolitic() {
        return politicsStatusMapper.selectAllPolitics();
    }
}
