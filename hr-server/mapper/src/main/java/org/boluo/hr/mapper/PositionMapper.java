package org.boluo.hr.mapper;

import org.boluo.hr.pojo.Position;

import java.util.List;


/**
 * 职位 数据层
 *
 * @author boluo
 */
public interface PositionMapper {
    /**
     * 删除职位
     *
     * @param id 职位id
     * @return 结果
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增职位
     *
     * @param position 职位信息
     * @return 结果
     */
    int insertPosition(Position position);

    /**
     * 查询职位
     *
     * @param id 职位id
     * @return 职位信息
     */
    Position selectByPrimaryKey(Integer id);

    /**
     * 修改职位
     *
     * @param position 职位信息
     * @return 结果
     */
    int updateByPrimaryKey(Position position);

    /**
     * 查询所有职位
     *
     * @return 职位集合
     */
    List<Position> selectAllPosition();

    /**
     * 批量删除
     *
     * @param ids 职位id数组
     * @return 结果
     */
    int deleteMany(Integer[] ids);
}