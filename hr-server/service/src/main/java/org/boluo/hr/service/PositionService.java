package org.boluo.hr.service;

import org.boluo.hr.mapper.PositionMapper;
import org.boluo.hr.pojo.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
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

    public List<Position> selectAllPostion() {
        return positionMapper.selectAllPosition();
    }

    public Position selectPostById(Integer id) {
        return positionMapper.selectByPrimaryKey(id);
    }

    public boolean delete(Integer id) {
        return positionMapper.deleteByPrimaryKey(id) == 1;
    }

    public boolean insert(Position pos) {
        return positionMapper.insertSelective(pos) == 1;
    }

    public boolean deleteMany(Integer[] ids) {
        return positionMapper.deleteMany(ids) == 1;
    }

    public boolean update(Position pos) {
        return positionMapper.updateByPrimaryKeySelective(pos) == 1;
    }
}
