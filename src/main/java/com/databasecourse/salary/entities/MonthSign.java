package com.databasecourse.salary.entities;

import javax.persistence.*;

/**
 * date:2016-05-26 21:53
 */
@Entity
@Table(name = "month_sign", schema = "")
public class MonthSign {
    private Integer id;
    private Integer work;
    private Integer absent;
    private Integer late;
    private Integer leave;
    private Integer overtime;
    private Integer month;
    private Integer year;
    private Employee employeeByEmployeeId;

    public MonthSign() {
    }

    public MonthSign(Employee employee,int month,int year) {
        this.employeeByEmployeeId = employee;
        this.work = 0;
        this.absent = 0 ;
        this.late = 0;
        this.leave = 0;
        this.overtime = 0;
        this.month= month;
        this.year = year;
    }

    @Override
    public String toString() {
        return "MonthSign{" +
                "id=" + id +
                ", work=" + work +
                ", absent=" + absent +
                ", late=" + late +
                ", leave=" + leave +
                ", overtime=" + overtime +
                ", month=" + month +
                ", year=" + year +
                ", employeeByEmployeeId=" + employeeByEmployeeId +
                '}';
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
    @Column(name = "work")
    public Integer getWork() {
        return work;
    }

    public void setWork(Integer work) {
        this.work = work;
    }

    @Basic
    @Column(name = "absent")
    public Integer getAbsent() {
        return absent;
    }

    public void setAbsent(Integer absent) {
        this.absent = absent;
    }

    @Basic
    @Column(name = "late")
    public Integer getLate() {
        return late;
    }

    public void setLate(Integer late) {
        this.late = late;
    }

    @Basic
    @Column(name = "leave_day")
    public Integer getLeave() {
        return leave;
    }

    public void setLeave(Integer leave) {
        this.leave = leave;
    }

    @Basic
    @Column(name = "overtime")
    public Integer getOvertime() {
        return overtime;
    }

    public void setOvertime(Integer overtime) {
        this.overtime = overtime;
    }

    @Basic
    @Column(name = "month")
    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    @Basic
    @Column(name = "year")
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MonthSign monthSign = (MonthSign) o;

        if (id != null ? !id.equals(monthSign.id) : monthSign.id != null) return false;
        if (work != null ? !work.equals(monthSign.work) : monthSign.work != null) return false;
        if (absent != null ? !absent.equals(monthSign.absent) : monthSign.absent != null) return false;
        if (late != null ? !late.equals(monthSign.late) : monthSign.late != null) return false;
        if (leave != null ? !leave.equals(monthSign.leave) : monthSign.leave != null) return false;
        if (overtime != null ? !overtime.equals(monthSign.overtime) : monthSign.overtime != null) return false;
        if (month != null ? !month.equals(monthSign.month) : monthSign.month != null) return false;
        if (year != null ? !year.equals(monthSign.year) : monthSign.year != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (work != null ? work.hashCode() : 0);
        result = 31 * result + (absent != null ? absent.hashCode() : 0);
        result = 31 * result + (late != null ? late.hashCode() : 0);
        result = 31 * result + (leave != null ? leave.hashCode() : 0);
        result = 31 * result + (overtime != null ? overtime.hashCode() : 0);
        result = 31 * result + (month != null ? month.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
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
