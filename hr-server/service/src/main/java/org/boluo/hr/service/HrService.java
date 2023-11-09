package org.boluo.hr.service;

import org.boluo.hr.mapper.HrMapper;
import org.boluo.hr.mapper.RoleMapper;
import org.boluo.hr.pojo.*;
import org.boluo.hr.util.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 人事 业务层
 *
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

    /**
     * 加载登录用户信息
     *
     * @param username 账号
     * @return UserDetails 账号详细信息
     * @throws UsernameNotFoundException 用户未找到异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Hr hr = hrMapper.loadByUserName(username);
        if (hr == null) {
            throw new UsernameNotFoundException("用户不存在!");
        }
        List<Hr> hrWithRoles = hrMapper.selectRoleWithHrById(hr.getId());
        hr.setRoles(hrWithRoles.get(0).getRoles());
        return hr;
    }

    /**
     * 获取除当前人事的其他人事
     *
     * @param currentId 当前人事id
     * @return 人事集合
     */
    public List<BaseHr> selectAllExceptCurrentHr(Integer currentId) {
        return hrMapper.selectAllExceptCurrentHr(currentId);
    }

    /**
     * 获取所有人事
     *
     * @return 人事集合
     */
    public List<Hr> selectAll() {
        return hrMapper.selectAll();
    }

    /**
     * 修改人事
     *
     * @param hr 人事信息
     * @return 结果
     */
    public boolean update(UploadHr hr) {
        return hrMapper.updateByPrimaryKey(hr) == 1;
    }

    /**
     * 删除人事
     *
     * @param id 人事id
     * @return 结果
     */
    public boolean delete(Integer id) {
        return hrMapper.deleteByPrimaryKey(id) == 1;
    }

    /**
     * 通过人事id 获取角色
     *
     * @param hid 人事id
     * @return 角色集合
     */
    public List<Role> selectRoles(Integer hid) {
        return roleMapper.selectByHrId(hid);
    }

    /**
     * 删除人事角色
     *
     * @param hrId 人事id
     * @return 结果
     */
    public boolean deleteRoleByHrId(Integer hrId) {
        return roleMapper.deleteByHrId(hrId) >= 0;
    }

    /**
     * 对人事添加角色
     *
     * @param hid     人事id
     * @param roleIds 角色id数组
     * @return 结果
     */
    public boolean insertRoles(Integer hid, Integer[] roleIds) {
        return roleMapper.insertRolesByRoleIds(hid, roleIds) > 0;
    }

    /**
     * 通过人事名称 获取人事
     *
     * @param name 人事名称
     * @return 人事集合
     */
    public List<Hr> selectHrByName(String name) {
        return hrMapper.selectHrByName(name);
    }

    /**
     * 新增人事
     *
     * @param insertHr 人事信息
     * @return 结果
     */
    public boolean insert(InsertHr insertHr) {
        return hrMapper.insertHr(insertHr) == 1;
    }

    /**
     * 通过用户名获取人事
     *
     * @param username 用户名
     * @return 人事
     */
    public Hr selectByUsername(String username) {
        return hrMapper.selectByUsername(username);
    }

    /**
     * 通过人事id获取人事
     *
     * @param id 人事id
     * @return 人事
     */
    public Hr selectById(Integer id) {
        return hrMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改人事角色
     *
     * @param uploadHrRole 人事角色信息
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean modifyRoles(UploadHrRole uploadHrRole) {
        if (deleteRoleByHrId(uploadHrRole.getHrId())) {
            if (CheckUtil.hasLength(uploadHrRole.getRoleIds())) {
                if (insertRoles(uploadHrRole.getHrId(), uploadHrRole.getRoleIds())) {
                    return true;
                }
                throw new RuntimeException("添加角色失败!");
            } else {
                // 未传角色id
                return true;
            }
        }
        return false;
    }
}
