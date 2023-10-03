package com.boluo.hr.service;

import org.boluo.hr.mapper.AppraiseMapper;
import org.boluo.hr.pojo.Appraise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author @1352955539(boluo)
 * @date 2021/2/28 - 21:40
 */
@Service
public class AppraiseService {

    @Autowired
    AppraiseMapper appraiseMapper;

    /**
     * @Description: 获取所有奖励
     * @Param: []
     * @Return: java.util.List<org.boluo.hr.pojo.Appraise>
     */
    public List<Appraise> getAll() {
        return appraiseMapper.selectAll();
    }
}
