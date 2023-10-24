package org.boluo.hr.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * 人事角色更新 实体
 *
 * @author 🍍
 * @date 2023/10/24
 */
@Getter
@Setter
public class UploadHrRole {
    private Integer hrId;
    private Integer[] roleIds;
}
