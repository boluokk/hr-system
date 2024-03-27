package org.boluo.hr.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @author boluo
 * @date 2023/11/08
 */
@Data
@Accessors(chain = true)
public class UploadDepartment {
    /**
     * 主键
     */
    @NotNull(message = "id不能为空")
    private Integer id;
    /**
     * 部门名称
     */
    private String name;
    /**
     * 父部门id
     */
    private Integer parentId;
    /**
     * 部门路径
     */
    private String depPath;
    /**
     * 是否开启
     */
    private Boolean enabled;
    /**
     * 是否父部门
     */
    private Boolean isParent;
}
