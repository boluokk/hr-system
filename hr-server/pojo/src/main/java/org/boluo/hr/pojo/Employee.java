package org.boluo.hr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 员工 实体
 *
 * @author boluo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 员工名称
     */
    private String name;
    /**
     * 性别
     */
    private String gender;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 婚姻状态
     */
    private String wedlock;
    /**
     * 民族id
     */
    private Integer nationId;
    /**
     * 籍贯
     */
    private String nativePlace;
    /**
     * 政治面貌
     */
    private Integer politicId;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 地址
     */
    private String address;
    /**
     * 部门id
     */
    private Integer departmentId;
    /**
     * 职称id
     */
    private Integer jobLevelId;
    /**
     * 职位id
     */
    private Integer posId;
    /**
     * 聘用形式
     */
    private String engageForm;
    /**
     * 最高学历
     */
    private String tiptopDegree;
    /**
     * 所属专业
     */
    private String specialty;
    /**
     * 学校
     */
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
    /**
     * 在职状态
     */
    private Nation nation;
    /**
     * 在职状态
     */
    private PoliticsStatus politicsStatus;
    /**
     * 部门
     */
    private Department department;
    /**
     * 职称
     */
    private JobLevel jobLevel;
    /**
     * 职位
     */
    private Position position;
    /**
     * 工资
     */
    private Salary salary;
}