package com.databasecourse.salary.daos.Impl;

/**
 * date:2016-05-26 22:04
 */

import org.springframework.stereotype.Repository;
import com.databasecourse.salary.daos.PositionDao;
import com.databasecourse.salary.entities.Position;

@Repository
public class PositionDaoImpl extends BaseDaoImpl<Position, Integer> implements PositionDao {

}