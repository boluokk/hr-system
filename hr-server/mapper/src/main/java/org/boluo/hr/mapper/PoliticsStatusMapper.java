package org.boluo.hr.mapper;

import org.boluo.hr.pojo.PoliticsStatus;

import java.util.List;

/**
 * 政治面貌 数据层
 *
 * @author boluo
 */
public interface PoliticsStatusMapper {

    /**
     * 删除政治面貌
     *
     * @param id 政治面貌id
     * @return 结果
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增政治面貌
     *
     * @param politicsStatus 政治面貌信息
     * @return 结果
     */
    int insertPoliticsStatus(PoliticsStatus politicsStatus);

    /**
     * 查询政治面貌
     *
     * @param id 政治面貌id
     * @return 政治面貌信息
     */
    PoliticsStatus selectByPrimaryKey(Integer id);

    /**
     * 修改政治面貌
     *
     * @param politicsStatus 政治面貌信息
     * @return 结果
     */
    int updateByPrimaryKey(PoliticsStatus politicsStatus);

    /**
     * 查询所有政治面貌
     *
     * @return 政治面貌集合
     */
    List<PoliticsStatus> selectAllPolitics();
}