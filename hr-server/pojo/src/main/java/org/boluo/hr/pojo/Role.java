package org.boluo.hr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色 实体
 *
 * @author boluo
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 角色名称(ex: ROLE_admin)
     */
    private String name;
    /**
     * 角色中文名称
     */
    private String nameZh;
}