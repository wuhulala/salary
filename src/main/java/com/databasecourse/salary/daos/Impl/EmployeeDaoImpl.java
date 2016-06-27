package com.databasecourse.salary.daos.Impl;

/**
 * date:2016-05-25 20:18
 */

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.databasecourse.salary.daos.EmployeeDao;
import com.databasecourse.salary.entities.Employee;

import java.sql.ResultSet;
import java.util.*;

@Repository
public class EmployeeDaoImpl extends BaseDaoImpl<Employee, Integer> implements EmployeeDao {

    public Map findByParams(Date timeStart, Date timeEnd, String searchInfo, int department, int position, int start, int length) {
        Criteria criteria = getSession().createCriteria(Employee.class);
        criteria.add(Restrictions.or(
                Restrictions.like("name","%"+searchInfo+"%"),
                Restrictions.like("phone","%"+searchInfo+"%"),
                Restrictions.like("idCard","%"+searchInfo+"%")))
                .add(Restrictions.between("joinTime", timeStart, timeEnd))
                .add(Restrictions.eq("state",0));
        if(department != -1){
            criteria.add(Restrictions.eq("departmentByDepartmentId.id",department));
        }
        if(position!=-1){
            criteria.add(Restrictions.eq("positionByPositionId.id",position));
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

    public List findActiveEmployees() {
        return getSession()
                .createCriteria(Employee.class)
                .add(Restrictions.eq("state",0))
                .list();
    }

    public Employee findByNameandPass(String username, String password) {
        return (Employee) getSession().createCriteria(Employee.class)
                .add(Restrictions.eq("phone",username))
                .add(Restrictions.eq("pass",password))
                .uniqueResult();
    }


    public void del(ArrayList<Integer> list) {
        String hql = "update Employee as s set state = 1 where ";

        for(int i = 0 ; i < list.size() ;i ++ ){
            if(i == 0){
                hql += "s.id = " + list.get(i);
            }else {
                hql += " or s.id = "+list.get(i);
            }
        }

        getSession().createQuery(hql).executeUpdate();
    }
}