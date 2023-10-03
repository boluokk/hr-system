package org.boluo.hr.pojo;

import java.util.Date;

public class Employeetrain {
    private Integer id;

    private Integer eid;

    private Date traindate;

    private String traincontent;

    private String remark;

    private String employeeName;

    public String getEmployeeName() {
        return employeeName;
    }

    @Override
    public String toString() {
        return "Employeetrain{" +
                "id=" + id +
                ", eid=" + eid +
                ", traindate=" + traindate +
                ", traincontent='" + traincontent + '\'' +
                ", remark='" + remark + '\'' +
                ", employeeName='" + employeeName + '\'' +
                '}';
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

    public Date getTraindate() {
        return traindate;
    }

    public void setTraindate(Date traindate) {
        this.traindate = traindate;
    }

    public String getTraincontent() {
        return traincontent;
    }

    public void setTraincontent(String traincontent) {
        this.traincontent = traincontent == null ? null : traincontent.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}