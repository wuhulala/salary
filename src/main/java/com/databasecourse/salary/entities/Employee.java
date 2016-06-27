package com.databasecourse.salary.entities;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

/**
 * date:2016-05-26 21:53
 */
@Entity
@Table(name = "employee")
public class Employee {
    private Integer id;
    private String name;
    private String phone;
    private Integer sex;
    private String bankNumber;
    private String idCard;
    private Date joinTime;
    private Float baseSalary;
    private String pass;
    private int state;
    private Collection<DaySign> daySignsById;
    private Department departmentByDepartmentId;
    private Position positionByPositionId;
    private Collection<MonthSalary> monthSalariesById;
    private Collection<MonthSign> monthSignsById;
    private Collection<YearSalary> yearSalariesById;

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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Basic
    @Column(name = "pass")
    @JSONField(serialize = false)
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Basic
    @Column(name = "state")
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "sex")
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "bank_number")
    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    @Basic
    @Column(name = "id_card")
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Basic
    @Column(name = "join_time")
    @JSONField(format = "yyyy-MM-dd")
    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    @Basic
    @Column(name = "base_salary")
    public Float getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Float baseSalary) {
        this.baseSalary = baseSalary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (id != null ? !id.equals(employee.id) : employee.id != null) return false;
        if (name != null ? !name.equals(employee.name) : employee.name != null) return false;
        if (phone != null ? !phone.equals(employee.phone) : employee.phone != null) return false;
        if (sex != null ? !sex.equals(employee.sex) : employee.sex != null) return false;
        if (bankNumber != null ? !bankNumber.equals(employee.bankNumber) : employee.bankNumber != null) return false;
        if (idCard != null ? !idCard.equals(employee.idCard) : employee.idCard != null) return false;
        if (joinTime != null ? !joinTime.equals(employee.joinTime) : employee.joinTime != null) return false;
        if (baseSalary != null ? !baseSalary.equals(employee.baseSalary) : employee.baseSalary != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (bankNumber != null ? bankNumber.hashCode() : 0);
        result = 31 * result + (idCard != null ? idCard.hashCode() : 0);
        result = 31 * result + (joinTime != null ? joinTime.hashCode() : 0);
        result = 31 * result + (baseSalary != null ? baseSalary.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "employeeByEmployeeId")
    @JSONField(serialize = false)

    public Collection<DaySign> getDaySignsById() {
        return daySignsById;
    }

    public void setDaySignsById(Collection<DaySign> daySignsById) {
        this.daySignsById = daySignsById;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    public Department getDepartmentByDepartmentId() {
        return departmentByDepartmentId;
    }

    public void setDepartmentByDepartmentId(Department departmentByDepartmentId) {
        this.departmentByDepartmentId = departmentByDepartmentId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id", referencedColumnName = "id")
    public Position getPositionByPositionId() {
        return positionByPositionId;
    }

    public void setPositionByPositionId(Position positionByPositionId) {
        this.positionByPositionId = positionByPositionId;
    }

    @OneToMany(mappedBy = "employeeByEmployeeId")
    @JSONField(serialize = false)

    public Collection<MonthSalary> getMonthSalariesById() {
        return monthSalariesById;
    }

    public void setMonthSalariesById(Collection<MonthSalary> monthSalariesById) {
        this.monthSalariesById = monthSalariesById;
    }

    @OneToMany(mappedBy = "employeeByEmployeeId")
    @JSONField(serialize = false)

    public Collection<MonthSign> getMonthSignsById() {
        return monthSignsById;
    }

    public void setMonthSignsById(Collection<MonthSign> monthSignsById) {
        this.monthSignsById = monthSignsById;
    }

    @OneToMany(mappedBy = "employeeByEmployeeId")
    @JSONField(serialize = false)

    public Collection<YearSalary> getYearSalariesById() {
        return yearSalariesById;
    }

    public void setYearSalariesById(Collection<YearSalary> yearSalariesById) {
        this.yearSalariesById = yearSalariesById;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", sex=" + sex +
                ", bankNumber='" + bankNumber + '\'' +
                ", idCard='" + idCard + '\'' +
                ", joinTime=" + joinTime +
                ", baseSalary=" + baseSalary +
                ", pass='" + pass + '\'' +
                ", state=" + state +
                '}';
    }
}
