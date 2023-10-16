package org.boluo.hr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 菜单 实体
 *
 * @author boluo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 请求通配路径
     */
    private String url;
    /**
     * 前端组件路径
     */
    private String path;
    /**
     * 组件名
     */
    private String component;
    /**
     * 菜单名字
     */
    private String name;
    /**
     * 菜单类名
     */
    private String iconClass;
    /**
     * 是否存活
     */
    private Boolean keepAlive;
    /**
     * 是否需要权限
     */
    private Boolean requireAuth;
    /**
     * 父菜单id
     */
    private Integer parentId;
    /**
     * 是否开启
     */
    private Boolean enabled;
    /**
     * 角色
     */
    private List<Role> roles;
    /**
     * 所含子菜单
     */
    private List<Menu> children;

}