package org.boluo.hr.service;

import org.boluo.hr.mapper.JoblevelMapper;
import org.boluo.hr.pojo.Joblevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 🍍
 * @date 2023/10/1
 */
@Service
public class JobLevelService {

    private final JoblevelMapper joblevelMapper;

    @Autowired
    public JobLevelService(JoblevelMapper joblevelMapper) {
        this.joblevelMapper = joblevelMapper;
    }

    public List<Joblevel> selectAll() {
        return joblevelMapper.selectAll();
    }

    public boolean insert(Joblevel job) {
        return joblevelMapper.insertSelective(job) == 1;
    }

    public boolean update(Joblevel job) {
        return joblevelMapper.updateByPrimaryKey(job) == 1;
    }

    public boolean delete(Integer id) {
        return joblevelMapper.deleteByPrimaryKey(id) == 1;
    }
}
