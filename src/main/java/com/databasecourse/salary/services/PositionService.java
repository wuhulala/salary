package com.databasecourse.salary.services;

/**
 * date:2016-05-26 22:06
 */

import com.databasecourse.salary.daos.Impl.PositionDaoImpl;
import org.springframework.stereotype.Service;
import com.databasecourse.salary.entities.Position;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PositionService extends BaseService<Position, Integer> {
    private PositionDaoImpl positionDao;

    public PositionDaoImpl getPositionDao() {
        return this.positionDao;
    }

    @Autowired
    public void setPositionDao(PositionDaoImpl positionDao) {
        super.setBaseDao(positionDao);
        this.positionDao = positionDao;
    }

}