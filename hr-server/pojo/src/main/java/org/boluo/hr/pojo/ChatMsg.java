package org.boluo.hr.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author @1352955539(boluo)
 * @date 2021/2/22 - 21:02
 */
public class ChatMsg implements Serializable {
    private String content;
    private Date date;
    private String from;
    private String to;

    private String NickName;

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getData() {
        return date;
    }

    public void setData(Date date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
