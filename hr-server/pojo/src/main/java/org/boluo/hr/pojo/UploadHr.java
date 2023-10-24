package org.boluo.hr.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * 人事更新 实体
 *
 * @author 🍍
 * @date 2023/10/24
 */
@Getter
@Setter
public class UploadHr {
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
