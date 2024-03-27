package org.boluo.hr.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 新增员工 实体
 *
 * @author boluo
 * @date 2023/11/08
 */
@Accessors(chain = true)
@Data
public class UploadEmployee {
    /**
     * 主键
     */
    @NotNull(message = "id不能为空")
    private Integer id;
    /**
     * 员工名称
     */
    @NotNull(message = "姓名不能为空")
    private String name;
    /**
     * 性别
     */
    @NotNull(message = "性别不能为空")
    private String gender;
    /**
     * 生日
     */
    @NotNull(message = "生日不能为空")
    private Date birthday;
    /**
     * 身份证号
     */
    @NotNull(message = "身份证号不能为空")
    private String idCard;
    /**
     * 婚姻状态
     */
    @NotNull(message = "婚姻状态不能为空")
    private String wedlock;
    /**
     * 民族id
     */
    @NotNull(message = "民族id不能为空")
    private Integer nationId;
    /**
     * 籍贯
     */
    @NotNull(message = "籍贯不能为空")
    private String nativePlace;
    /**
     * 政治面貌
     */
    @NotNull(message = "政治面貌不能为空")
    private Integer politicId;
    /**
     * 邮箱
     */
    @NotNull(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;
    /**
     * 手机号
     */
    @NotNull(message = "手机号不能为空")
    private String phone;
    /**
     * 地址
     */
    @NotNull(message = "地址不能为空")
    private String address;
    /**
     * 部门id
     */
    @NotNull(message = "部门id不能为空")
    private Integer departmentId;
    /**
     * 职称id
     */
    @NotNull(message = "职称id不能为空")
    private Integer jobLevelId;
    /**
     * 职位id
     */
    @NotNull(message = "职位id不能为空")
    private Integer posId;
    /**
     * 聘用形式
     */
    @NotNull(message = "聘用形式不能为空")
    private String engageForm;
    /**
     * 最高学历
     */
    @NotNull(message = "最高学历不能为空")
    private String tiptopDegree;
    /**
     * 所属专业
     */
    @NotNull(message = "所属专业不能为空")
    private String specialty;
    /**
     * 学校
     */
    @NotNull(message = "学校不能为空")
    private String school;
    /**
     * 入职日期
     */
    private Date beginDate;
    /**
     * 在职状态
     */
    private String workState;
    /**
     * 工号
     */
    private String workId;
    /**
     * 合同期限
     */
    private Double contractTerm;
    /**
     * 转正日期
     */
    private Date conversionTime;
    /**
     * 离职日期
     */
    private Date notWorkDate;
    /**
     * 合同起始日期
     */
    private Date beginContract;
    /**
     * 合同结束日期
     */
    private Date endContract;
    /**
     * 工龄
     */
    private Integer workAge;
}
