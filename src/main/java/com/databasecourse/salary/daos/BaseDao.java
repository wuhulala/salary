package com.databasecourse.salary.daos;

import org.hibernate.Criteria;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * BaseDao接口
 * Created by Administrator on 2016/3/6.
 */
public interface BaseDao<T,PK extends Serializable> {
    /**
     * 根据实体保存更新
     * @param t
     */
    void saveOrUpdate(T t);

    /**
     * 根据id删除
     * @param pk
     */
    void del(PK pk);

    /**
     * 根据实体删除
     * @param t
     */
    void del(T t);

    /**
     * 根据实体id集合批量删除
     * @param list
     */
    void del(ArrayList<PK> list);


    /**
     * 根据id查找
     * @param pk
     * @return
     */
    T findById(PK pk);

    /**
     * 根据id批量查找
     * @param list
     * @return
     */
    List findById(ArrayList<PK> list);

    /**
     * 获取实体类所有数据
     * @return
     */
    List<T> findAll();

    /**
     * 分页
     * @param start
     * @param count
     * @return
     */
    List<T> findByPage(int start, int count);


    /**
     * 分页获取
     * @param start
     * @param length
     * @return
     */
    Map findByPaging(int start, int length);

    /**
     * 根据查询条件获取
     * @param start
     * @param length
     * @return
     */
    Map findByPaging(int start, int length, Criteria criteria);


    /**
     * 获取全部的数量
     * @return
     */
    Long getTotalNumber();

    /**
     * 根据多个相等条件获取准确的实体
     * @param params
     * @return
     */
    public T findOneByParams(Map<String, Object> params);

    /**
     * 加载一个id为pk的实体 不需要查询数据库
     * @param pk
     * @return
     */
    public T load(PK pk);

    /**
     * 批量插入
     * @param objs
     */
    void saveOrUpdate(List<T> objs);

}
