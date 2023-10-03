package com.boluo.hr.service;

import org.boluo.hr.mapper.HrMapper;
import org.boluo.hr.mapper.RoleMapper;
import org.boluo.hr.pojo.Hr;
import org.boluo.hr.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author @1352955539(boluo)
 * @date 2021/1/25 - 16:33
 */
@Service
public class HrService implements UserDetailsService {

    @Autowired
    HrMapper hrMapper;

    @Autowired
    RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Hr hr = hrMapper.loadByUserName(s);
        if (hr == null) {
            throw new UsernameNotFoundException("用户不存在!");
        }
        List<Hr> hrWithRoles = hrMapper.getRoleWithHrid(hr.getId());
        hr.setRoles(hrWithRoles.get(0).getRoles());
        return hr;
    }

    public List<Hr> getAllExceptCurrentHr(Integer currentId) {
        return hrMapper.selectAllExceptCurrentHr(currentId);
    }

    public List<Hr> getHrs() {
        return hrMapper.selectBypageSize();
    }

    public int updateHr(Hr hr) {
        return hrMapper.updateByPrimaryKeySelective(hr);
    }

    public int deleteHr(Integer id) {
        return hrMapper.deleteByPrimaryKey(id);
    }

    public List<Role> getRoles(Integer hid) {
        return roleMapper.selectByHrId(hid);
    }

    public int deleteRoleByHrid(Integer hrid) {
        return roleMapper.deleteByHrId(hrid);
    }

    public int insertRoles(Integer hid, Integer[] roleIds) {
        return roleMapper.insertRolesByRoleIds(hid, roleIds);
    }

    public List<Hr> selectSomeHrsByHrName(String name) {
        return hrMapper.selectByHrName(name);
    }

    public int addHr(Hr hr) {
        return hrMapper.insertSelective(hr);
    }
}
