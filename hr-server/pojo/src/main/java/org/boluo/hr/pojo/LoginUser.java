package org.boluo.hr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录账户 实体
 *
 * @author 🍍
 * @date 2023/10/23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser {
    /**
     * 账号
     */
    String username;
    /**
     * 密码
     */
    String password;
}
