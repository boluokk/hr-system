package org.boluo.hr.mapper;

import org.boluo.hr.pojo.Appraise;

import java.util.List;


/**
 * 考评 数据层
 *
 * @author boluo
 */

public interface AppraiseMapper {
    /**
     * 通过考评id 删除考评
     *
     * @param id 考评id
     * @return 结果
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增考评
     *
     * @param appraise 考评信息
     * @return 结果
     */
    int insertAppraise(Appraise appraise);

    /**
     * 通过考评id 查询考评
     *
     * @param id
     * @return 考评信息
     */
    Appraise selectByPrimaryKey(Integer id);

    /**
     * 修改考评
     *
     * @param appraise 考评信息
     * @return 结果
     */
    int updateByPrimaryKey(Appraise appraise);

    /**
     * 查询所有考评
     *
     * @return 考评集合
     */
    List<Appraise> selectAll();
}