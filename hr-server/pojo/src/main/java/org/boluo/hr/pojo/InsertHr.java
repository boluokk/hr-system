package org.boluo.hr.pojo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author boluo
 * @date 2023/11/09
 */
@Data
public class InsertHr {
    /**
     * 名称
     */
    @NotNull(message = "名称不能为空")
    private String name;
    /**
     * 手机
     */
    @NotNull(message = "手机不能为空")
    private String phone;
    /**
     * 电话
     */
    @NotNull(message = "电话不能为空")
    private String telephone;
    /**
     * 住址
     */
    @NotNull(message = "住址不能为空")
    private String address;
    /**
     * 是否可用
     */
    private Boolean enabled;
    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空")
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
