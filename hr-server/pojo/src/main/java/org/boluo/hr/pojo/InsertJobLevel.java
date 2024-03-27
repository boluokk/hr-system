package org.boluo.hr.pojo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @author boluo
 * @date 2023/11/09
 */
@Data
public class InsertJobLevel {
    /**
     * 名称
     */
    @NotNull(message = "名称不能为空")
    private String name;
    /**
     * 等级标题
     */
    @NotNull(message = "等级标题不能为空")
    @Pattern(regexp = "初级|中级|副高级|正高级|员级", message = "等级只能是初级、中级、副高级、正高级、员级")
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
