package com.databasecourse.salary.daos.Impl;

/**
 * date:2016-05-26 15:18
 */

import com.databasecourse.salary.entities.DaySign;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.databasecourse.salary.daos.MonthSignDao;
import com.databasecourse.salary.entities.MonthSign;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MonthSignDaoImpl extends BaseDaoImpl<MonthSign, Integer> implements MonthSignDao {
    public MonthSign findByEmployeeId(Integer id,int year,int month){
        String hql = "from MonthSign as m where m.employeeByEmployeeId.id = "+id+" and m.year = "+year + " and m.month = "+month;
        return (MonthSign) getSession().createQuery(hql).uniqueResult();
    }

    public Map findByParams(int year, int month, String searchInfo, int department, int position,  int start, int length) {

        Criteria criteria = getSession().createCriteria(MonthSign.class);
        criteria.createAlias("employeeByEmployeeId","employee")
                .add(Restrictions.or(
                        Restrictions.like("employee.name", "%" + searchInfo + "%"),
                        Restrictions.like("employee.phone", "%" + searchInfo + "%"),
                        Restrictions.like("employee.idCard", "%" + searchInfo + "%")))
                .add(Restrictions.eq("year", year))
                .add(Restrictions.eq("month", month));
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

    public List findByParams(int year, int month) {
        return getSession().createCriteria(MonthSign.class)
                .add(Restrictions.eq("year", year))
                .add(Restrictions.eq("month", month))
                .list();
    }
}