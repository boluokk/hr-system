package org.boluo.hr.pojo;

import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author boluo
 * @date 2023/11/09
 */
@Data
public class UploadJobLevel {
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
     * 等级标题
     */
    @NotNull(message = "等级标题不能为空")
    private String titleLevel;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 是否开启
     */
    private Boolean enabled;
}
