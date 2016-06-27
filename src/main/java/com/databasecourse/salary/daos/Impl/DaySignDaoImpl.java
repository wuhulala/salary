package com.databasecourse.salary.daos.Impl;

/**
 * date:2016-05-26 10:57
 */

import com.databasecourse.salary.entities.Employee;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.databasecourse.salary.daos.DaySignDao;
import com.databasecourse.salary.entities.DaySign;

import java.util.*;

@Repository
public class DaySignDaoImpl extends BaseDaoImpl<DaySign, Integer> implements DaySignDao {
    public Map findByParams(Date timeStart, Date timeEnd, String searchInfo, int department, int position,int state, int start, int length) {
        Criteria criteria = getSession().createCriteria(DaySign.class);
        criteria.createAlias("employeeByEmployeeId","employee")
                .add(Restrictions.or(
                        Restrictions.like("employee.name", "%" + searchInfo + "%"),
                        Restrictions.like("employee.phone", "%" + searchInfo + "%"),
                        Restrictions.like("employee.idCard", "%" + searchInfo + "%")))
                .add(Restrictions.between("date", timeStart, timeEnd));
        if(department!=-1){
            criteria.add(Restrictions.eq("employee.departmentByDepartmentId.id",department));
        }
        if(position!=-1){
            criteria.add(Restrictions.eq("employee.positionByPositionId.id",position));
        }
        if(state!=-1){
            criteria.add(Restrictions.eq("state",state));
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

    public void updateDao(ArrayList<Integer> chks, int state) {
        String hql = "update DaySign as s set s.state = "+state+" where ";
        for(int i = 0 ; i < chks.size() ;i ++ ){
            if(i == 0){
                hql += "s.id = " + chks.get(i);
            }else {
                hql += " or s.id = "+chks.get(i);
            }
        }
        getSession().createQuery(hql).executeUpdate();
    }

    public List findByDate(Date date) {
        return getSession().createCriteria(DaySign.class)
                .add(Restrictions.eq("date",date))
                .list();
    }

    public List findByParams(Date start, Date end, Integer id) {
        return getSession().createCriteria(DaySign.class)
                .add(Restrictions.eq("employeeByEmployeeId.id",id))
                .add(Restrictions.between("date", start,end))
                .list();
    }

    public DaySign findByDate(Date date, Integer id) {
        return (DaySign) getSession().createCriteria(DaySign.class)
                .add(Restrictions.eq("employeeByEmployeeId.id",id))
                .add(Restrictions.eq("date", date))
                .uniqueResult();
    }
}