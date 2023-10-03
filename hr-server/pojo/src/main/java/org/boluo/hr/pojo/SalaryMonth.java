package org.boluo.hr.pojo;

import java.util.Date;

/**
 * @author @1352955539(boluo)
 * @date 2021/3/25 - 22:19
 */
public class SalaryMonth {
    private int id;
    private String salaryName;
    private Date salaryTime;
    private int allSalary;
    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSalaryName() {
        return salaryName;
    }

    public void setSalaryName(String salaryName) {
        this.salaryName = salaryName;
    }

    public Date getSalaryTime() {
        return salaryTime;
    }

    public void setSalaryTime(Date salaryTime) {
        this.salaryTime = salaryTime;
    }

    public int getAllSalary() {
        return allSalary;
    }

    public void setAllSalary(int allSalary) {
        this.allSalary = allSalary;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
