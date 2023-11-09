package org.boluo.hr.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
    @NotNull(message = "id不能为空")
    @Min(value = 1, message = "id不能小于1")
    private Integer id;
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
