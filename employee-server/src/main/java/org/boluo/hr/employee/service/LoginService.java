package org.boluo.hr.employee.service;

import org.boluo.hr.employee.pojo.User;

/**
 * @author boluo
 * @date 2024/02/09
 */
public interface LoginService {
    User login(String workId, String password);
}
