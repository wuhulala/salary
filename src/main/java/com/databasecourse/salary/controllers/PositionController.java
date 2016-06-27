package com.databasecourse.salary.controllers;

/**
 * date:2016-05-26 22:09
 */

import com.databasecourse.salary.services.PositionService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.databasecourse.salary.entities.Admin;
import com.databasecourse.salary.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@Scope("prototype")
public class PositionController {
    private Map dataMap;
    private PositionService positionService;

    public PositionService getPositionService() {
        return this.positionService;
    }

    @Autowired
    public void setPositionService(PositionService positionService) {
        this.positionService = positionService;
        dataMap = new HashMap();
    }

    @RequestMapping("getallposition.json")
    @ResponseBody
    public Map getAllPosition(){
        dataMap.put("all",positionService.findAll());
        return dataMap;
    }
}