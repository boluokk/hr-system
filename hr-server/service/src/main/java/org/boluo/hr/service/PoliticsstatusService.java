package org.boluo.hr.service;

import org.boluo.hr.mapper.PoliticsstatusMapper;
import org.boluo.hr.pojo.Politicsstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 🍍
 * @date 2023/10/1
 */
@Service
public class PoliticsstatusService {

    private final PoliticsstatusMapper politicsstatusMapper;

    @Autowired
    public PoliticsstatusService(PoliticsstatusMapper politicsstatusMapper) {
        this.politicsstatusMapper = politicsstatusMapper;
    }

    public List<Politicsstatus> selectAllPolitic() {
        return politicsstatusMapper.selectAllPlitic();
    }
}
