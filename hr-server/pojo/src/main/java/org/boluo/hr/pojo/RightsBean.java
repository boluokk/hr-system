package org.boluo.hr.pojo;

import java.util.Arrays;

/**
 * @author @1352955539(boluo)
 * @date 2021/2/8 - 13:12
 */
public class RightsBean {
    private Integer[] ids;
    private Integer roleID;

    public RightsBean(Integer[] ids, Integer roleID) {
        this.ids = ids;
        this.roleID = roleID;
    }

    public RightsBean() {
    }

    public Integer[] getIds() {
        return ids;
    }

    @Override
    public String toString() {
        return "RightsBean{" +
                "ids=" + Arrays.toString(ids) +
                ", roleID=" + roleID +
                '}';
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }
}
