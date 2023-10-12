package org.boluo.hr.service;

import org.boluo.hr.mapper.AppraiseMapper;
import org.boluo.hr.pojo.Appraise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 考评 业务层
 *
 * @author 🍍
 * @date 2023/10/1
 */
@Service
public class AppraiseService {

    private final AppraiseMapper appraiseMapper;

    @Autowired
    public AppraiseService(AppraiseMapper appraiseMapper) {
        this.appraiseMapper = appraiseMapper;
    }

    /**
     * 返回所有考评
     * @return 考评集合
     */
    public List<Appraise> selectAll() {
        return appraiseMapper.selectAll();
    }
}
