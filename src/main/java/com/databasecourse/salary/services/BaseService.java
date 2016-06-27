package com.databasecourse.salary.services;

import com.databasecourse.salary.daos.Impl.BaseDaoImpl;
import com.databasecourse.salary.entities.MonthSalary;
import org.hibernate.Criteria;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public abstract class BaseService<T,PK extends Serializable>{

    private BaseDaoImpl<T,PK> baseDao;

    public BaseDaoImpl<T, PK> getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(BaseDaoImpl<T, PK> baseDao) {
        this.baseDao = baseDao;
    }

    public void saveOrUpdate(T t) {
        baseDao.saveOrUpdate(t);
    }

    public void del(PK pk) {
        baseDao.del(pk);
    }


    public void del(T t) {
        baseDao.del(t);
    }

    public void del(ArrayList<PK> list) {
        baseDao.del(list);
    }

    public T findById(PK pk) {
        return (T) baseDao.findById(pk);
    }

    public List findById(ArrayList<PK> list) {
        return baseDao.findById(list);
    }

    public List<T> findAll() {
        return baseDao.findAll();
    }

    public List<T> findByPage(int start, int count) {
        return baseDao.findByPage(start, count);
    }

    public Map findByPaging(int start, int length) {
        return baseDao.findByPaging(start, length);
    }

    public Map findByPaging(int start, int length, Criteria criteria) {
        return baseDao.findByPaging(start, length, criteria);
    }

    public Long getTotalNumber() {
        return baseDao.getTotalNumber();
    }


}
