package com.databasecourse.salary.daos.Impl;

/**
 * date:2016-05-26 23:19
 */

import com.databasecourse.salary.entities.MonthSign;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.databasecourse.salary.daos.MonthAllowanceDao;
import com.databasecourse.salary.entities.MonthAllowance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MonthAllowanceDaoImpl extends BaseDaoImpl<MonthAllowance, Integer> implements MonthAllowanceDao {

    public Map findByParams(int department, int position, int start, int length) {
        System.out.println(start+"-------"+length);
        Criteria criteria = getSession().createCriteria(MonthAllowance.class);
        if(department!=-1){
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

    public MonthAllowance findByParams(int department, int position) {
        return (MonthAllowance) getSession()
                .createCriteria(MonthAllowance.class)
                .add(Restrictions.eq("departmentByDepartmentId.id",department))
                .add(Restrictions.eq("positionByPositionId.id",position))
                .uniqueResult();
    }
}