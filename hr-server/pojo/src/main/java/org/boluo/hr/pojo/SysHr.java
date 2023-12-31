package org.boluo.hr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysHr {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 电话号
     */
    private String telephone;
    /**
     * 住址
     */
    private String address;
    /**
     * 是否开启
     */
    private Boolean enabled;
    /**
     * 用户名
     */
    private String username;
    /**
     * 头像
     */
    private String userFace;
    /**
     * 备注
     */
    private String remark;
    /**
     * 角色
     */
    private List<Role> roles;
}
