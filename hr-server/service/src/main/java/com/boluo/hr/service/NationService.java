package com.boluo.hr.service;

import com.boluo.hr.mapper.NationMapper;
import com.boluo.hr.pojo.Nation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author @1352955539(boluo)
 * @date 2021/2/17 - 17:29
 */
@Service
public class NationService {
    @Autowired
    private NationMapper nationMapper;

    public List<Nation> getAllNation() {
        return nationMapper.selectAll();
    }
}
