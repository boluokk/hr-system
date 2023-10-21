package org.boluo.hr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 基础人事 实体
 *
 * @author 🍍
 * @date 2023/10/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseHr {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 手机
     */
    private String phone;
    /**
     * 电话
     */
    private String telephone;
    /**
     * 住址
     */
    private String address;
    /**
     * 是否可用
     */
    private Boolean enabled;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 头像
     */
    private String userFace;
    /**
     * 备注
     */
    private String remark;
}
