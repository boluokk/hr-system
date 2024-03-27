package org.boluo.hr.employee.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import org.boluo.hr.employee.mapper.UserMapper;
import org.boluo.hr.employee.pojo.User;
import org.boluo.hr.employee.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author boluo
 * @date 2024/02/09
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User login(String workId, String password) {
        User user = userMapper.findByWorkId(workId);
        if (ObjectUtil.isNull(user)) {
            return null;
        }
        if (!user.getPassword().equals(SecureUtil.md5(password))) {
            return null;
        }
        user.setPassword(null);
        return user;
    }
}
