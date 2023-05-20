package com.boluo.hr.service;

import com.boluo.hr.mapper.JoblevelMapper;
import com.boluo.hr.pojo.Joblevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author @1352955539(boluo)
 * @date 2021/2/6 - 18:03
 */
@Service
public class JobLevelService {

    @Autowired
    private JoblevelMapper joblevelMapper;

//    获取所有数据
    public List<Joblevel> getALLJobLevel() {
        return joblevelMapper.getAllJoBLevel();
    }

//    添加
    public int insertJobByJobLevelPojo(Joblevel job) {
        return joblevelMapper.insertSelective(job);
    }

//    编辑s
    public int updateJobByJobPojo(Joblevel job) {
        return joblevelMapper.updateByPrimaryKey(job);
    }

//    删除
    public int deleteJobById(Integer id) {
        return joblevelMapper.deleteByPrimaryKey(id);
    }
}
