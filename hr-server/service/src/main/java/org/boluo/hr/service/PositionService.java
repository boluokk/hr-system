package com.boluo.hr.service;

import org.boluo.hr.mapper.PositionMapper;
import org.boluo.hr.pojo.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author @1352955539(boluo)
 * @date 2021/2/4 - 22:02
 */

@Service
public class PositionService {

    @Autowired
    private PositionMapper positionMapper;

    public List<Position> getAllPostion() {
       return positionMapper.getAllPostion();
    }

    public Position getPostByid(Integer id) {
        return positionMapper.selectByPrimaryKey(id);
    }

    public int deleteById(Integer id) {
        return positionMapper.deleteByPrimaryKey(id);
    }

    public int insertByPos(Position pos) {
        return positionMapper.insertSelective(pos);
    }

    public int deleteMany(Integer[] ids) {
        return positionMapper.deleteMany(ids);
    }

    public int updateByPos(Position pos) {
       return positionMapper.updateByPrimaryKeySelective(pos);
    }
}
