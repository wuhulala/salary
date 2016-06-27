package com.databasecourse.salary.controllers;

/**
 * date:2016-05-26 11:03
 */

import com.databasecourse.salary.services.DaySignService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.databasecourse.salary.entities.Admin;
import com.databasecourse.salary.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@Scope("prototype")
public class DaySignController {
    private Map dataMap;
    private DaySignService daySignService;

    @InitBinder
    public void InitBinder(WebDataBinder dataBinder){
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM");
        dateFormatter.setLenient(false);
        dataBinder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormatter,true));
    }
    @Autowired
    public void setDaySignService(DaySignService daySignService) {
        this.daySignService = daySignService;
        dataMap = new HashMap();
    }

    @RequestMapping("getalldaysign.json")
    @ResponseBody
    public Map getAllDaySign(int start,int length,Date timeStart,String searchInfo,int state,int department,int position){
        dataMap = daySignService.findByParams(timeStart,searchInfo,department,position,state,start,length);
        return dataMap;
    }

    @RequestMapping("getdaysignbyid.json")
    @ResponseBody
    public Map getEmployee(@RequestParam("id")int id){
        dataMap.put("data", daySignService.findById(id));
        return dataMap;
    }

    @RequestMapping("editdaysignstate")
    @ResponseBody
    public Map editDaySignState(@RequestParam("chk")ArrayList<Integer> chks,@RequestParam("year")int year,@RequestParam("month")int month,@RequestParam("state")int state,@RequestParam("editinfo")String editInfo){
        daySignService.editDaySignState(chks,state,year,month,editInfo);
        dataMap.put("message","修改成功");
        return  dataMap;
    }

}