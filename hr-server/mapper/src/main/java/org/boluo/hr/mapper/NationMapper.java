package org.boluo.hr.mapper;

import org.boluo.hr.pojo.Nation;

import java.util.List;

/**
 * 民族 数据层
 *
 * @author boluo
 */
public interface NationMapper {

    /**
     * 删除民族
     *
     * @param id 民族id
     * @return 结果
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增民族
     *
     * @param nation 民族信息
     * @return 结果
     */
    int insertNation(Nation nation);

    /**
     * 查询民族
     *
     * @param id 民族id
     * @return 民族信息
     */
    Nation selectByPrimaryKey(Integer id);

    /**
     * 修改民族
     *
     * @param nation 民族信息
     * @return 结果
     */
    int updateByPrimaryKey(Nation nation);

    /**
     * 查询所有民族
     *
     * @return 民族集合
     */
    List<Nation> selectAll();
}