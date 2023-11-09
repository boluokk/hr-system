package org.boluo.hr.service;

import org.boluo.hr.mapper.JobLevelMapper;
import org.boluo.hr.pojo.InsertJobLevel;
import org.boluo.hr.pojo.JobLevel;
import org.boluo.hr.pojo.UploadJobLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 职称等级 业务层
 *
 * @author 🍍
 * @date 2023/10/1
 */
@Service
public class JobLevelService {

    private final JobLevelMapper joblevelMapper;

    @Autowired
    public JobLevelService(JobLevelMapper joblevelMapper) {
        this.joblevelMapper = joblevelMapper;
    }

    /**
     * 查询所有职称等级
     *
     * @return 职称等级集合
     */
    public List<JobLevel> selectAll() {
        return joblevelMapper.selectAll();
    }

    /**
     * 新增职称等级
     *
     * @param insertJobLevel 职称等级信息
     * @return 结果
     */
    public boolean insert(InsertJobLevel insertJobLevel) {
        return joblevelMapper.insertJobLevel(insertJobLevel) == 1;
    }

    /**
     * 修改职称等级
     *
     * @param uploadJobLevel 职称等级信息
     * @return 结果
     */
    public boolean update(UploadJobLevel uploadJobLevel) {
        return joblevelMapper.updateByPrimaryKey(uploadJobLevel) == 1;
    }

    /**
     * 删除职称等级
     *
     * @param id 职称等级id
     * @return 结果
     */
    public boolean delete(Integer id) {
        return joblevelMapper.deleteByPrimaryKey(id) == 1;
    }
}
