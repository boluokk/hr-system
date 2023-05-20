package com.boluo.hr.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Joblevel implements Serializable {
    private Integer id;

    private String name;

    private String titlelevel;

    private Date createdate;

    private Boolean enabled;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTitlelevel() {
        return titlelevel;
    }

    public void setTitlelevel(String titlelevel) {
        this.titlelevel = titlelevel == null ? null : titlelevel.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Joblevel)) return false;
        Joblevel joblevel = (Joblevel) o;
        return name.equals(joblevel.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}