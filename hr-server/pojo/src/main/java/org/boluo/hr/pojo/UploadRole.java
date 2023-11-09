package org.boluo.hr.pojo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author boluo
 * @date 2023/11/09
 */
@Data
public class UploadRole {
    /**
     * 主键
     */
    @NotNull(message = "id不能为空")
    private Integer id;
    /**
     * 角色名称(ex: ROLE_admin)
     */
    @NotNull(message = "角色名称不能为空")
    private String name;
    /**
     * 角色中文名称
     */
    @NotNull(message = "角色中文名称不能为空")
    private String nameZh;
}
