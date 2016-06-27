package com.databasecourse.salary.entities;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.Collection;

/**
 * date:2016-05-26 21:53
 */
@Entity
@Table(name = "department")
public class Department {
    private Integer id;
    private String name;
    private Collection<Employee> employeesById;
    private Collection<MonthAllowance> monthAllowancesById;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "departmentByDepartmentId")
    @JSONField(serialize = false)

    public Collection<Employee> getEmployeesById() {
        return employeesById;
    }

    public void setEmployeesById(Collection<Employee> employeesById) {
        this.employeesById = employeesById;
    }

    @OneToMany(mappedBy = "departmentByDepartmentId")
    @JSONField(serialize = false)

    public Collection<MonthAllowance> getMonthAllowancesById() {
        return monthAllowancesById;
    }

    public void setMonthAllowancesById(Collection<MonthAllowance> monthAllowancesById) {
        this.monthAllowancesById = monthAllowancesById;
    }
}
