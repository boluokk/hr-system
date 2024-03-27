package org.boluo.hr.employee.pojo;

import lombok.Data;
import java.util.Date;

/**
 * @author boluo
 * @version 1.0.0
 * @date 2024/02/09
 */

@Data
public class Leave {
    private int id;
    private int employeeId;
    private Date requestStartTime;
    private Date requestEndTime;
    private int status;

    public Date getRequestStartTime() {
        return requestStartTime;
    }

    public void setRequestStartTime(Date requestStartTime) {
        this.requestStartTime = requestStartTime;
    }

    public Date getRequestEndTime() {
        return requestEndTime;
    }

    public void setRequestEndTime(Date requestEndTime) {
        this.requestEndTime = requestEndTime;
    }
}
