package org.boluo.hr.pojo;

import java.util.List;

/**
 * @author @1352955539(boluo)
 * @date 2021/2/10 - 23:07
 */
public class RespEmpBean {
    private Integer total;
    private List<Employee> employees;

    private Integer pageNum;
    private Integer pageSize;



    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "RespEmpBean{" +
                "total=" + total +
                ", employees=" + employees +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
