package com.databasecourse.salary.entities;

import javax.persistence.*;

/**
 * date:2016-05-26 21:53
 */
@Entity
@Table(name = "month_allowance", schema = "")
public class MonthAllowance {
    private Integer id;
    private Float housing;
    private Float award;
    private Float absentMoney;
    private Float lateMoney;
    private Float leaveMoney;
    private Float overtimeMoney;
    private Position positionByPositionId;
    private Department departmentByDepartmentId;

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
    @Column(name = "housing")
    public Float getHousing() {
        return housing;
    }

    public void setHousing(Float housing) {
        this.housing = housing;
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
    @Column(name = "overtime_money")
    public Float getOvertimeMoney() {
        return overtimeMoney;
    }

    public void setOvertimeMoney(Float overtimeMoney) {
        this.overtimeMoney = overtimeMoney;
    }

    @Basic
    @Column(name = "award")
    public Float getAward() {
        return award;
    }

    public void setAward(Float award) {
        this.award = award;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MonthAllowance that = (MonthAllowance) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (housing != null ? !housing.equals(that.housing) : that.housing != null) return false;
        if (award != null ? !award.equals(that.award) : that.award != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (housing != null ? housing.hashCode() : 0);
        result = 31 * result + (award != null ? award.hashCode() : 0);
        return result;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id", referencedColumnName = "id")
    public Position getPositionByPositionId() {
        return positionByPositionId;
    }

    public void setPositionByPositionId(Position positionByPositionId) {
        this.positionByPositionId = positionByPositionId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    public Department getDepartmentByDepartmentId() {
        return departmentByDepartmentId;
    }

    public void setDepartmentByDepartmentId(Department departmentByDepartmentId) {
        this.departmentByDepartmentId = departmentByDepartmentId;
    }
}
