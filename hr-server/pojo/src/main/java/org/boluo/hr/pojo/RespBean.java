package org.boluo.hr.pojo;

/**
 * 响应 实体
 *
 * @author @1352955539(boluo)
 * @date 2021/1/31 - 19:38
 */
public class RespBean {
    private Integer status;
    private String msg;
    private Object obj;

    public static RespBean ok(String msg, Object obj) {
        return new RespBean(200, msg, obj);
    }

    public static RespBean ok(Object obj) {
        return new RespBean(200, obj);
    }

    public static RespBean ok(String... msg) {
        if (msg.length > 0) {
            return new RespBean(200, msg[0], null);
        }
        return new RespBean(200, "操作成功", null);
    }

    public static RespBean error(String... msg) {
        if (msg.length > 0) {
            return new RespBean(500, msg[0], null);
        }
        return new RespBean(500, "操作失败", null);
    }

    public RespBean() {
    }

    public RespBean(Integer status, Object obj) {
        this.status = status;
        this.obj = obj;
    }

    public RespBean(Integer status, String msg, Object obj) {
        this.status = status;
        this.msg = msg;
        this.obj = obj;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
