package org.boluo.hr.pojo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author boluo
 * @date 2023/11/09
 */
@Data
public class InsertPosition {
    /**
     * 主键
     */
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
