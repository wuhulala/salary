package com.databasecourse.salary.entities;

import javax.persistence.*;

/**
 * date:2016-05-26 21:53
 */
@Entity
@Table(name = "year_salary", schema = "")
public class YearSalary {
    private Integer id;
    private String everyMonthSalary;
    private Float yearAward;
    private Float totalSalary;
    private int year ;
    private Employee employeeByEmployeeId;

    public YearSalary(Employee employee, int year, String monthSalarys) {
        this.employeeByEmployeeId = employee;
        this.everyMonthSalary = monthSalarys;
        this.yearAward = Float.valueOf(0);
        this.totalSalary = Float.valueOf(0);
        this.year = year;
    }

    public YearSalary() {
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
    @Column(name = "every_month_salary")
    public String getEveryMonthSalary() {
        return everyMonthSalary;
    }

    public void setEveryMonthSalary(String everyMonthSalary) {
        this.everyMonthSalary = everyMonthSalary;
    }

    @Basic
    @Column(name = "year_award")
    public Float getYearAward() {
        return yearAward;
    }

    public void setYearAward(Float yearAward) {
        this.yearAward = yearAward;
    }

    @Basic
    @Column(name = "total_salary")
    public Float getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(Float totalSalary) {
        this.totalSalary = totalSalary;
    }

    @Basic
    @Column(name = "year")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        YearSalary that = (YearSalary) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (everyMonthSalary != null ? !everyMonthSalary.equals(that.everyMonthSalary) : that.everyMonthSalary != null)
            return false;
        if (yearAward != null ? !yearAward.equals(that.yearAward) : that.yearAward != null) return false;
        if (totalSalary != null ? !totalSalary.equals(that.totalSalary) : that.totalSalary != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (everyMonthSalary != null ? everyMonthSalary.hashCode() : 0);
        result = 31 * result + (yearAward != null ? yearAward.hashCode() : 0);
        result = 31 * result + (totalSalary != null ? totalSalary.hashCode() : 0);
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
