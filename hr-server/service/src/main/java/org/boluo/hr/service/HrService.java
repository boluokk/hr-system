package org.boluo.hr.service;

import org.boluo.hr.mapper.HrMapper;
import org.boluo.hr.mapper.RoleMapper;
import org.boluo.hr.pojo.Hr;
import org.boluo.hr.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author 🍍
 * @date 2023/10/1
 */
@Service
public class HrService implements UserDetailsService {

    private final HrMapper hrMapper;

    private final RoleMapper roleMapper;

    @Autowired
    public HrService(HrMapper hrMapper, RoleMapper roleMapper) {
        this.hrMapper = hrMapper;
        this.roleMapper = roleMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Hr hr = hrMapper.loadByUserName(s);
        if (hr == null) {
            throw new UsernameNotFoundException("用户不存在!");
        }
        List<Hr> hrWithRoles = hrMapper.getRoleWithHrId(hr.getId());
        hr.setRoles(hrWithRoles.get(0).getRoles());
        return hr;
    }

    public List<Hr> getAllExceptCurrentHr(Integer currentId) {
        return hrMapper.selectAllExceptCurrentHr(currentId);
    }

    public List<Hr> selectAll() {
        return hrMapper.selectAll();
    }

    public boolean update(Hr hr) {
        return hrMapper.updateByPrimaryKeySelective(hr) == 1;
    }

    public boolean delete(Integer id) {
        return hrMapper.deleteByPrimaryKey(id) == 1;
    }

    public List<Role> selectRoles(Integer hid) {
        return roleMapper.selectByHrId(hid);
    }

    public boolean deleteRoleByHrid(Integer hrId) {
        return roleMapper.deleteByHrId(hrId) >= 0;
    }

    public boolean insertRoles(Integer hid, Integer[] roleIds) {
        return roleMapper.insertRolesByRoleIds(hid, roleIds) > 0;
    }

    public List<Hr> selectHrByName(String name) {
        return hrMapper.selectHrByName(name);
    }

    public boolean insert(Hr hr) {
        return hrMapper.insertSelective(hr) == 1;
    }
}
