package com.databasecourse.salary.entities;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.sql.Date;

/**
 * date:2016-05-26 21:53
 */
@Entity
@Table(name = "day_sign", schema = "")
public class DaySign {
    private Integer id;
    private Date date;
    private Integer state;
    private Employee employeeByEmployeeId;

    @Override
    public String toString() {
        return "DaySign{" +
                "id=" + id +
                ", date=" + date +
                ", state=" + state +
                ", employeeByEmployeeId=" + employeeByEmployeeId +
                '}';
    }

    public DaySign() {
    }

    public DaySign(Employee employee) {
        this.date = new Date(new java.util.Date().getTime());
        this.state = 9999;
        this.employeeByEmployeeId = employee;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date")
    @JSONField(format = "yyyy-MM-dd")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "state")
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DaySign daySign = (DaySign) o;

        if (id != null ? !id.equals(daySign.id) : daySign.id != null) return false;
        if (date != null ? !date.equals(daySign.date) : daySign.date != null) return false;
        if (state != null ? !state.equals(daySign.state) : daySign.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    public Employee getEmployeeByEmployeeId() {
        return employeeByEmployeeId;
    }

    public void setEmployeeByEmployeeId(Employee employeeByEmployeeId) {
        this.employeeByEmployeeId = employeeByEmployeeId;
    }
}
