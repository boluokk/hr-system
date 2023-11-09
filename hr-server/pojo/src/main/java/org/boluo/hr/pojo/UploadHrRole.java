package org.boluo.hr.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * 人事角色更新 实体
 *
 * @author 🍍
 * @date 2023/10/24
 */
@Getter
@Setter
public class UploadHrRole {
    @NotNull(message = "人事id不能为空")
    private Integer hrId;
    private Integer[] roleIds;
}
