package com.boluo.hr.service;

import org.boluo.hr.mapper.PoliticsstatusMapper;
import org.boluo.hr.pojo.Politicsstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author @1352955539(boluo)
 * @date 2021/2/17 - 17:33
 */
@Service
public class PoliticsstatusService {

    @Autowired
    PoliticsstatusMapper politicsstatusMapper;

    public List<Politicsstatus> getAllPolitic() {
        return politicsstatusMapper.selectAllPlitic();
    }
}
