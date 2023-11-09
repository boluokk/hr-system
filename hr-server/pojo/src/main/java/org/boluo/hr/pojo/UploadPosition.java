package org.boluo.hr.pojo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author boluo
 * @date 2023/11/09
 */
@Data
public class UploadPosition {
    /**
     * 主键
     */
    @NotNull(message = "主键不能为空")
    @Min(value = 1, message = "主键必须大于0")
    private Integer id;
    /**
     * 名称
     */
    @NotNull(message = "名称不能为空")
    private String name;
    /**
     * 创建时间
     */
    @NotNull(message = "创建时间不能为空")
    private Date createDate;
    /**
     * 是否开启
     */
    private Boolean enabled;
}
