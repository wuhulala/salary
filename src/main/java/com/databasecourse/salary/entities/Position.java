package com.databasecourse.salary.entities;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.Collection;

/**
 * date:2016-05-26 21:53
 */
@Entity
@Table(name = "position")

public class Position {
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

        Position position = (Position) o;

        if (id != null ? !id.equals(position.id) : position.id != null) return false;
        if (name != null ? !name.equals(position.name) : position.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "positionByPositionId")
    @JSONField(serialize = false)
    public Collection<Employee> getEmployeesById() {
        return employeesById;
    }

    public void setEmployeesById(Collection<Employee> employeesById) {
        this.employeesById = employeesById;
    }

    @OneToMany(mappedBy = "positionByPositionId")
    @JSONField(serialize = false)
    public Collection<MonthAllowance> getMonthAllowancesById() {
        return monthAllowancesById;
    }

    public void setMonthAllowancesById(Collection<MonthAllowance> monthAllowancesById) {
        this.monthAllowancesById = monthAllowancesById;
    }
}
