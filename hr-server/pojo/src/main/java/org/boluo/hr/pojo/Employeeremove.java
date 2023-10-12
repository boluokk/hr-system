package org.boluo.hr.pojo;

import java.util.Date;

public class Employeeremove {
    private Integer id;

    private Integer eid;

    private Integer afterdepid;

    private Integer afterjobid;

    private Integer beforedepid;

    private Integer beforejobid;

    private Date removedate;

    private String reason;

    private String remark;

    public Integer getBeforejobid() {
        return beforejobid;
    }

    public void setBeforejobid(Integer beforejobid) {
        this.beforejobid = beforejobid;
    }

    public Integer getBeforedepid() {
        return beforedepid;
    }

    public void setBeforedepid(Integer beforedepid) {
        this.beforedepid = beforedepid;
    }

    private String employeeName;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Integer getAfterdepid() {
        return afterdepid;
    }

    public void setAfterdepid(Integer afterdepid) {
        this.afterdepid = afterdepid;
    }

    public Integer getAfterjobid() {
        return afterjobid;
    }

    public void setAfterjobid(Integer afterjobid) {
        this.afterjobid = afterjobid;
    }

    public Date getRemovedate() {
        return removedate;
    }

    public void setRemovedate(Date removedate) {
        this.removedate = removedate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}