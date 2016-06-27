package com.databasecourse.salary.tests;

import com.alibaba.fastjson.JSON;
import com.databasecourse.salary.entities.DaySign;
import com.databasecourse.salary.entities.MonthSalary;
import com.databasecourse.salary.entities.MonthSign;
import com.databasecourse.salary.services.DaySignService;
import com.databasecourse.salary.services.MonthSalaryService;
import com.databasecourse.salary.services.MonthSignService;
import com.databasecourse.salary.utils.CalendarEvent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring-common.xml" })
public class ProjectTst {
    @Autowired
    private DaySignService daySignService ;
    @Autowired
    private MonthSalaryService monthSalaryService ;
    @Autowired
    private MonthSignService monthSignService ;
    @Test
    public void test() throws ParseException {
        //daySignService.del(5);
        //monthSalaryService.insertMonthSalary();
     /*   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse("2016-05-01");
        Date date2 = sdf.parse("2016-06-01");
        List<DaySign>list = daySignService.findByParams(date1, date2, 6);
        List<CalendarEvent>calendars = new ArrayList<CalendarEvent>();

        for(DaySign daySign:list){
            calendars.add(new CalendarEvent(daySign.getDate(),daySign.getState()));
        }

        System.out.println(JSON.toJSONString(calendars));*/
        //daySignService.checkDaySign();
        //monthSalaryService.insertMonthSalary();
       // monthSignService.insertMonthSigns();
       // daySignService.checkDaySign();
    }
}