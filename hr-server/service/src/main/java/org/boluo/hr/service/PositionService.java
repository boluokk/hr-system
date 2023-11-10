package org.boluo.hr.service;

import org.boluo.hr.mapper.PositionMapper;
import org.boluo.hr.pojo.InsertPosition;
import org.boluo.hr.pojo.Position;
import org.boluo.hr.pojo.UploadPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 职位 业务层
 *
 * @author 🍍
 * @date 2023/10/1
 */
@Service
public class PositionService {

    private final PositionMapper positionMapper;

    @Autowired
    public PositionService(PositionMapper positionMapper) {
        this.positionMapper = positionMapper;
    }

    /**
     * 查询所有职位
     *
     * @return 职位集合
     */
    public List<Position> selectAllPosition() {
        return positionMapper.selectAllPosition();
    }

    /**
     * 查询职位
     *
     * @param id 职位id
     * @return 职位信息
     */
    public Position selectPositionById(Integer id) {
        return positionMapper.selectByPrimaryKey(id);
    }

    /**
     * 删除职位
     *
     * @param id 职位id
     * @return 结果
     */
    public boolean delete(Integer id) {
        return positionMapper.deleteByPrimaryKey(id) == 1;
    }

    /**
     * 新增职位
     *
     * @param insertPosition 职位信息
     * @return 结果
     */
    public boolean insert(InsertPosition insertPosition) {
        return positionMapper.insertPosition(insertPosition) == 1;
    }

    /**
     * 批量删除
     *
     * @param ids 职位id数组
     * @return 结果
     */
    public boolean deleteMany(Integer[] ids) {
        return positionMapper.deleteMany(ids) == ids.length;
    }

    /**
     * 修改职位
     *
     * @param uploadPosition 职位信息
     * @return 结果
     */
    public boolean update(UploadPosition uploadPosition) {
        return positionMapper.updateByPrimaryKey(uploadPosition) == 1;
    }

    public Position findByName(String name) {
        return positionMapper.findByName(name);
    }
}
