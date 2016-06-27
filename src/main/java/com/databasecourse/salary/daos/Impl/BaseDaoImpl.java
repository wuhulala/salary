package com.databasecourse.salary.daos.Impl;


import com.databasecourse.salary.daos.BaseDao;
import com.databasecourse.salary.utils.ReflectUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实现基本的增删改查操作
 * @param <T>  泛型类
 * @param <PK> primary key
 */
public class BaseDaoImpl<T,PK extends Serializable> implements BaseDao<T,PK> {


	@Autowired
	private SessionFactory sessionFactory;
	//Class<?>:防止报黄线,意思是传入任意类型都行,无所谓,也可以使用Object
	private Class<?> entityClass;

	public BaseDaoImpl(){
		//new出一个子类对象，父类中的this是子类中的this
		entityClass = ReflectUtil.getGenericParameterType(this.getClass());
	}

	public void saveOrUpdate(T t){
		getSession().saveOrUpdate(t);
	}

	public void del(PK pk){
		getSession().delete(findById(pk));
	}

	public void del(T t){
		getSession().delete(t);
	}


	public void del(ArrayList<PK> list) {
		String entityName = entityClass.getName();
		String hql = "delete  "+entityName+" s where ";

		for(int i = 0 ; i < list.size() ;i ++ ){
			if(i == 0){
				hql += "s.id = " + list.get(i);
			}else {
				hql += " or s.id = "+list.get(i);
			}
		}

		getSession().createQuery(hql).executeUpdate();
	}

	public T findById(PK pk){
		return (T) getSession().get(entityClass, pk);
	}


	public List findById(ArrayList<PK> list) {
		String entityName = entityClass.getName();
		String hql = "from  "+entityName+" s where ";

		for(int i = 0 ; i < list.size() ;i ++ ){
			if(i == 0){
				hql += "s.id = " + list.get(i);
			}else {
				hql += " or s.id = "+list.get(i);
			}
		}

		System.out.println(hql);
		return getSession().createQuery(hql).list();
	}

	public List<T> findAll(){
		String hql = "from "+entityClass.getName();
		return getSession().createQuery(hql).list();
	}


	public List<T> findByPage(int start,int count){
		Criteria criteria = getSession().createCriteria(entityClass);
		criteria.setFirstResult(start);
		criteria.setMaxResults(count);
		return criteria.list();
	}


	public Map findByPaging(int start, int length) {
		Criteria criteria = getSession().createCriteria(entityClass);
		Long lens = getTotalNumber();
		Map map = new HashMap();
		map.put("data",criteria.setFirstResult(start).setMaxResults(length).list());
		map.put("recordsTotal",lens);
		map.put("recordsFiltered", lens);
		return map;
	}



	public Map findByPaging(int start, int length, Criteria criteria) {


		Long lens = getTotalNumber();
		Long recordsFiltered = (Long)criteria.setProjection(Projections.rowCount()).uniqueResult();
		criteria.setProjection(null);

		criteria.setFirstResult(start).setMaxResults(length);

		List result = criteria.list();

		Map map = new HashMap();
		map.put("data",result);
		map.put("recordsTotal",lens);
		map.put("recordsFiltered", recordsFiltered);

		return map;
	}


	public Long getTotalNumber() {
		Long number = (Long) getSession().createCriteria(entityClass).setProjection(Projections.rowCount()).uniqueResult();
		return number;
	}

	public T findOneByParams(Map<String, Object> params) {
		Criteria c = getSession().createCriteria(entityClass);

		for (String key : params.keySet()) {
			c.add(Restrictions.eq(key, params.get(key)));
		}
		return  (T)c.uniqueResult();
	}

	public T load(PK pk) {
		return (T) getSession().get(entityClass,pk);
	}

	public void saveOrUpdate(List<T> objs) {
		int count = 0 ;
		for (T t : objs) {
			getSession().saveOrUpdate(t);
			if(++count%100==0){
				getSession().flush();
				getSession().clear();
			}
		}
	}

	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}