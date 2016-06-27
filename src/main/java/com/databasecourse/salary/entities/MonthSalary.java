package com.databasecourse.salary.entities;

import javax.persistence.*;

/**
 * date:2016-05-26 21:53
 */
@Entity
@Table(name = "month_salary", schema = "")
public class MonthSalary {
    private Integer id;
    private Float salary;
    private Integer month;
    private Integer year;
    private Float base;
    private Float overtimeMoney;
    private Float absentMoney;
    private Float lateMoney;
    private Float leaveMoney;
    private Float housingMoney;
    private Float awardMoney;
    private Employee employeeByEmployeeId;



    public MonthSalary() {
    }

    public MonthSalary(Employee employee, int year, int month, float salary, Float baseSalary, Float award, Float housing, float overtimeMoney, float lateMoney, float leaveMoney, float absentMoney) {
        this.salary = salary;
        this.month = month;
        this.year = year;
        this.base = baseSalary;
        this.overtimeMoney = overtimeMoney;
        this.absentMoney = absentMoney;
        this.lateMoney = lateMoney;
        this.leaveMoney = leaveMoney;
        this.housingMoney = housing;
        this.awardMoney = award;
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
    @Column(name = "salary")
    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
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

    @Basic
    @Column(name = "base")
    public Float getBase() {
        return base;
    }

    public void setBase(Float base) {
        this.base = base;
    }

    @Basic
    @Column(name = "overtime_money")
    public Float getOvertimeMoney() {
        return overtimeMoney;
    }

    public void setOvertimeMoney(Float overtimeMoney) {
        this.overtimeMoney = overtimeMoney;
    }

    @Basic
    @Column(name = "absent_money")
    public Float getAbsentMoney() {
        return absentMoney;
    }

    public void setAbsentMoney(Float absentMoney) {
        this.absentMoney = absentMoney;
    }

    @Basic
    @Column(name = "late_money")
    public Float getLateMoney() {
        return lateMoney;
    }

    public void setLateMoney(Float lateMoney) {
        this.lateMoney = lateMoney;
    }

    @Basic
    @Column(name = "leave_money")
    public Float getLeaveMoney() {
        return leaveMoney;
    }

    public void setLeaveMoney(Float leaveMoney) {
        this.leaveMoney = leaveMoney;
    }

    @Basic
    @Column(name = "housing_money")
    public Float getHousingMoney() {
        return housingMoney;
    }

    public void setHousingMoney(Float housingMoney) {
        this.housingMoney = housingMoney;
    }

    @Basic
    @Column(name = "award_money")
    public Float getAwardMoney() {
        return awardMoney;
    }

    public void setAwardMoney(Float awardMoney) {
        this.awardMoney = awardMoney;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MonthSalary that = (MonthSalary) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (salary != null ? !salary.equals(that.salary) : that.salary != null) return false;
        if (month != null ? !month.equals(that.month) : that.month != null) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;
        if (base != null ? !base.equals(that.base) : that.base != null) return false;
        if (overtimeMoney != null ? !overtimeMoney.equals(that.overtimeMoney) : that.overtimeMoney != null)
            return false;
        if (absentMoney != null ? !absentMoney.equals(that.absentMoney) : that.absentMoney != null) return false;
        if (lateMoney != null ? !lateMoney.equals(that.lateMoney) : that.lateMoney != null) return false;
        if (leaveMoney != null ? !leaveMoney.equals(that.leaveMoney) : that.leaveMoney != null) return false;
        if (housingMoney != null ? !housingMoney.equals(that.housingMoney) : that.housingMoney != null) return false;
        if (awardMoney != null ? !awardMoney.equals(that.awardMoney) : that.awardMoney != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        result = 31 * result + (month != null ? month.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (base != null ? base.hashCode() : 0);
        result = 31 * result + (overtimeMoney != null ? overtimeMoney.hashCode() : 0);
        result = 31 * result + (absentMoney != null ? absentMoney.hashCode() : 0);
        result = 31 * result + (lateMoney != null ? lateMoney.hashCode() : 0);
        result = 31 * result + (leaveMoney != null ? leaveMoney.hashCode() : 0);
        result = 31 * result + (housingMoney != null ? housingMoney.hashCode() : 0);
        result = 31 * result + (awardMoney != null ? awardMoney.hashCode() : 0);
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
