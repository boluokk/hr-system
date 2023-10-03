package org.boluo.hr.pojo;

/**
 * @author @1352955539(boluo)
 * @date 2021/2/9 - 13:50
 */
public class DepartRequestBean {
    private Integer parentId;
    private String parentDepPath;
    private Boolean parentIsParent;

    private Integer childrenId;
    private String childrenName;
    private Boolean childrenEnabled;

    public Integer getChildrenId() {
        return childrenId;
    }

    public void setChildrenId(Integer childrenId) {
        this.childrenId = childrenId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getParentDepPath() {
        return parentDepPath;
    }

    public void setParentDepPath(String parentDepPath) {
        this.parentDepPath = parentDepPath;
    }

    public Boolean getParentIsParent() {
        return parentIsParent;
    }

    public void setParentIsParent(Boolean parentIsParent) {
        this.parentIsParent = parentIsParent;
    }

    public String getChildrenName() {
        return childrenName;
    }

    public Boolean getChildrenEnabled() {
        return childrenEnabled;
    }

    public void setChildrenEnabled(Boolean childrenEnabled) {
        this.childrenEnabled = childrenEnabled;
    }

    public void setChildrenName(String childrenName) {
        this.childrenName = childrenName;
    }


    @Override
    public String toString() {
        return "DepartRequestBean{" +
                "parentId=" + parentId +
                ", parentDepPath='" + parentDepPath + '\'' +
                ", parentIsParent=" + parentIsParent +
                ", childrenName='" + childrenName + '\'' +
                ", childrenEnabled=" + childrenEnabled +
                '}';
    }
}
