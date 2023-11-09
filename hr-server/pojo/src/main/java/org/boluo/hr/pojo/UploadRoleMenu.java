package org.boluo.hr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 更新角色权限 实体
 *
 * @author 🍍
 * @date 2023/10/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadRoleMenu {
    @NotNull(message = "角色ID不能为空")
    private Integer roleId;
    private Integer[] menuIds;
}

