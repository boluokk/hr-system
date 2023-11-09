package org.boluo.hr.pojo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author boluo
 * @date 2023/11/08
 */
@Data
public class UploadSalary {
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
     * 基本工资
     */
    @NotNull(message = "基本工资不能为空")
    @Min(value = 1, message = "基本工资不能小于1")
    private Integer basicSalary;
    /**
     * 奖金
     */
    @NotNull(message = "奖金不能为空")
    @Min(value = 1, message = "奖金不能小于1")
    private Integer bonus;
    /**
     * 午餐补助
     */
    @NotNull(message = "午餐补助不能为空")
    @Min(value = 1, message = "午餐补助不能小于1")
    private Integer lunchSalary;
    /**
     * 交通补助
     */
    @NotNull(message = "交通补助不能为空")
    @Min(value = 1, message = "交通补助不能小于1")
    private Integer trafficSalary;
    /**
     * 应发工资
     */
    private Integer allSalary;
    /**
     * 养老金基数
     */
    @NotNull(message = "养老金基数不能为空")
    @Min(value = 1, message = "养老金基数不能小于1")
    private Integer pensionBase;
    /**
     * 养老金比率
     */
    @NotNull(message = "养老金比率不能为空")
    @Min(value = 0, message = "养老金比率不能小于0")
    private Float pensionPer;
    /**
     * 创建时间
     */
    @NotNull(message = "创建时间不能为空")
    private Date createDate;
    /**
     * 医疗基数
     */
    @NotNull(message = "医疗基数不能为空")
    @Min(value = 1, message = "医疗基数不能小于1")
    private Integer medicalBase;
    /**
     * 医疗比率
     */
    @NotNull(message = "医疗比率不能为空")
    @Min(value = 0, message = "医疗比率不能小于0")
    private Float medicalPer;
    /**
     * 公积金基数
     */
    @NotNull(message = "公积金基数不能为空")
    @Min(value = 1, message = "公积金基数不能小于1")
    private Integer accumulationFundBase;
    /**
     * 公积金比率
     */
    @NotNull(message = "公积金比率不能为空")
    @Min(value = 0, message = "公积金比率不能小于0")
    private Float accumulationFundPer;
}
