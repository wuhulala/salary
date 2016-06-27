package com.databasecourse.salary.daos.Impl;

/**
 * date:2016-05-28 13:13
 */

import com.databasecourse.salary.entities.DaySign;
import com.databasecourse.salary.entities.MonthAllowance;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.databasecourse.salary.daos.MonthSalaryDao;
import com.databasecourse.salary.entities.MonthSalary;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MonthSalaryDaoImpl extends BaseDaoImpl<MonthSalary, Integer> implements MonthSalaryDao {

    public Map findByParams(String searchInfo, int year, int month, int department, int position, int start, int length) {
        Criteria criteria = getSession().createCriteria(MonthSalary.class);
        criteria.createAlias("employeeByEmployeeId","employee")
                .add(Restrictions.or(
                        Restrictions.like("employee.name", "%" + searchInfo + "%"),
                        Restrictions.like("employee.phone", "%" + searchInfo + "%"),
                        Restrictions.like("employee.idCard", "%" + searchInfo + "%")))
                .add(Restrictions.eq("year",year))
                .add(Restrictions.eq("month",month));

        if(department!=-1){
            criteria.add(Restrictions.eq("employee.departmentByDepartmentId.id",department));
        }
        if(position!=-1){
            criteria.add(Restrictions.eq("employee.positionByPositionId.id",position));
        }

        Long lens = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
        criteria.setProjection(null);
        criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);//设置查询结果为实体对象，
        List result = criteria.setFirstResult(start).setMaxResults(length).list();
        Map map = new HashMap();
        map.put("recordsFiltered",lens);
        map.put("recordsTotal",lens);
        map.put("data",result);
        return map;
    }

    public MonthSalary findByParams(int year, int month, Integer id) {
        return (MonthSalary) getSession().createCriteria(MonthSalary.class)
                .add(Restrictions.eq("year",year))
                .add(Restrictions.eq("month",month))
                .add(Restrictions.eq("employeeByEmployeeId.id",id))
                .uniqueResult();
    }

}