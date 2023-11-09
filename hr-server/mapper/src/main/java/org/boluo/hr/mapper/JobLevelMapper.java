package org.boluo.hr.mapper;

import org.boluo.hr.pojo.InsertJobLevel;
import org.boluo.hr.pojo.JobLevel;
import org.boluo.hr.pojo.UploadJobLevel;

import java.util.List;

/**
 * 职称等级 数据层
 *
 * @author boluo
 */
public interface JobLevelMapper {

    /**
     * 删除职称等级
     *
     * @param id 职称等级id
     * @return 结果
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增职称等级
     *
     * @param insertJobLevel 职称等级信息
     * @return 结果
     */
    int insertJobLevel(InsertJobLevel insertJobLevel);

    /**
     * 通过id 查询职称等级
     *
     * @param id 职称等级id
     * @return 职称等级信息
     */
    JobLevel selectByPrimaryKey(Integer id);


    /**
     * 修改职称等级
     *
     * @param UploadJobLevel 职称等级信息
     * @return 结果
     */
    int updateByPrimaryKey(UploadJobLevel uploadJobLevel);

    /**
     * 查询所有职称等级
     *
     * @return 职称集合
     */
    List<JobLevel> selectAll();
}