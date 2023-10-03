package org.boluo.hr.service;

import org.boluo.hr.mapper.NationMapper;
import org.boluo.hr.pojo.Nation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 🍍
 * @date 2023/10/1
 */
@Service
public class NationService {
    private final NationMapper nationMapper;

    @Autowired
    public NationService(NationMapper nationMapper) {
        this.nationMapper = nationMapper;
    }

    public List<Nation> selectAllNation() {
        return nationMapper.selectAll();
    }
}
