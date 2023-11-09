package org.boluo.hr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 考评 实体
 *
 * @author boluo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appraise {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 员工id
     */
    private Integer employeeId;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 考评结果
     */
    private String appraiseResult;
    /**
     * 考评内容
     */
    private String appraiseContent;
    /**
     * 备注
     */
    private String remark;
    /**
     * 员工信息
     */
    private UploadEmployee employee;
}