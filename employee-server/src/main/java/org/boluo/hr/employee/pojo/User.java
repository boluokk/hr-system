package org.boluo.hr.employee.pojo;

import lombok.Data;

/**
 * @author boluo
 * @date 2024/02/09
 */
@Data
public class User {
    private int userId;
    private String name;
    private String workId;
    private String password;

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
