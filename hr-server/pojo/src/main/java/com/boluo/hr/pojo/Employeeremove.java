package com.boluo.hr.pojo;

import java.util.Date;

public class Employeeremove {
    private Integer id;

    private Integer eid;

    private Integer afterdepid;

    private Integer afterjobid;

    private Date removedate;

    private String reason;

    private String remark;

    private String oldDepartName;

    private String oldJoblevelName;

    private String newDepartName;

    private String newJoblevelName;

    public String getOldDepartName() {
        return oldDepartName;
    }

    public void setOldDepartName(String oldDepartName) {
        this.oldDepartName = oldDepartName;
    }

    public String getOldJoblevelName() {
        return oldJoblevelName;
    }

    public void setOldJoblevelName(String oldJoblevelName) {
        this.oldJoblevelName = oldJoblevelName;
    }

    public String getNewDepartName() {
        return newDepartName;
    }

    public void setNewDepartName(String newDepartName) {
        this.newDepartName = newDepartName;
    }

    public String getNewJoblevelName() {
        return newJoblevelName;
    }

    public void setNewJoblevelName(String newJoblevelName) {
        this.newJoblevelName = newJoblevelName;
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