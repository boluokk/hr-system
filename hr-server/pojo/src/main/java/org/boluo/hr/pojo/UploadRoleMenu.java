package org.boluo.hr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Integer roleId;
    private Integer[] menuIds;
}

