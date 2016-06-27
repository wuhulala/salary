package com.databasecourse.salary.services;

/**
 * date:2016-05-26 11:00
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.databasecourse.salary.daos.Impl.DaySignDaoImpl;
import com.databasecourse.salary.daos.Impl.EmployeeDaoImpl;
import com.databasecourse.salary.daos.Impl.MonthSignDaoImpl;
import com.databasecourse.salary.daos.MonthSignDao;
import com.databasecourse.salary.entities.Employee;
import com.databasecourse.salary.entities.MonthSign;
import com.databasecourse.salary.utils.CalendarEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.databasecourse.salary.daos.DaySignDao;
import com.databasecourse.salary.entities.DaySign;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DaySignService extends BaseService<DaySign, Integer> {
    private DaySignDaoImpl daySignDao;

    public DaySignDaoImpl getDaySignDao() {
        return this.daySignDao;
    }

    @Autowired
    public void setDaySignDao(DaySignDaoImpl daySignDao) {
        super.setBaseDao(daySignDao);
        this.daySignDao = daySignDao;
    }

    @Autowired
    private MonthSignDaoImpl monthSignDao;
    @Autowired
    private EmployeeDaoImpl employeeDao;

    public Map findByParams(Date timeStart, String searchInfo, int department, int position,int state, int start, int length) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timeStart);//设置当前日期
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        Date timeEnd = calendar.getTime();
        return daySignDao.findByParams(timeStart,timeEnd,searchInfo,department,position,state,start,length);
    }

    public void editDaySignState(ArrayList<Integer> chks, int state, int year,int month,String editInfo) {
        daySignDao.updateDao(chks, state);
        List<Map<String, Object>>lists= JSON.parseObject(editInfo, new TypeReference<List<Map<String, Object>>>() {
        });
        for(Map map : lists){

            int state0 = Integer.valueOf(map.get("state0").toString()) ;
            int state1 = Integer.valueOf(map.get("state1").toString()) ;
            int state2 = Integer.valueOf(map.get("state2").toString()) ;
            int state3 = Integer.valueOf(map.get("state3").toString()) ;
            int id = Integer.valueOf(map.get("id").toString()) ;

            MonthSign monthSign = monthSignDao.findByEmployeeId(id, year, month);

            if(state == 0 ){
                monthSign.setAbsent(monthSign.getAbsent()-state3);
                monthSign.setLate(monthSign.getLate() - state1);
                monthSign.setLeave(monthSign.getLeave() - state2);
                monthSign.setWork(monthSign.getWork()  + state1 + state2 + state3);
            }else if(state == 1){
                monthSign.setAbsent(monthSign.getAbsent()-state3);
                monthSign.setLate(monthSign.getLate() +state1+state0 + state2 + state3);
                monthSign.setLeave(monthSign.getLeave() - state2);
                monthSign.setWork(monthSign.getWork() - state0 );
            }else if(state == 2){
                monthSign.setAbsent(monthSign.getAbsent()-state3);
                monthSign.setLate(monthSign.getLate() - state1);
                monthSign.setLeave(monthSign.getLeave()  + state0 + state1  + state3);
                monthSign.setWork(monthSign.getWork() - state0);
            }else if(state == 3){
                monthSign.setAbsent(monthSign.getAbsent()+ state0 + state1 + state2);
                monthSign.setLate(monthSign.getLate() - state1);
                monthSign.setLeave(monthSign.getLeave() - state2 );
                monthSign.setWork(monthSign.getWork() - state0 );
            }

            monthSignDao.saveOrUpdate(monthSign);
        }
    }

    /**
     * 每天凌晨插入员工签到
     */
    @Scheduled(cron="0 0 3 * * ?")   //每天凌晨一点
    public void insertDaySign(){

        List<DaySign> daySigns = new ArrayList<DaySign>();
        List<Employee>employees = employeeDao.findActiveEmployees();
        for(Employee employee : employees){
            DaySign daySign = new DaySign(employee);
            daySigns.add(daySign);
        }

        daySignDao.saveOrUpdate(daySigns);

    }

    /**
     * 每天早上10点一分检查员工签到
     */
    @Scheduled(cron="0 1 10 * * ?")
    public void checkDaySign(){
        List<DaySign> daySigns = daySignDao.findByDate(new Date());
        List<DaySign> daySigns1 = new ArrayList<DaySign>();
        List<MonthSign> monthSigns = new ArrayList<MonthSign>();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());//设置当前日期
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        for(DaySign daySign : daySigns){
            System.out.println(daySign);
            if (daySign.getState() == 9999){
                daySign.setState(3);
                MonthSign monthSign = monthSignDao.findByEmployeeId(daySign.getEmployeeByEmployeeId().getId(),year,month);
                System.out.println(monthSign);
                monthSign.setAbsent(monthSign.getAbsent() + 1);
                monthSigns.add(monthSign);
                daySigns1.add(daySign);
            }
        }
        monthSignDao.saveOrUpdate(monthSigns);
        daySignDao.saveOrUpdate(daySigns1);

    }

    public List findByParams(Date start, Date end, Integer id) {
        List<DaySign>list = daySignDao.findByParams(start, end, id);
        List<CalendarEvent>calendars = new ArrayList<CalendarEvent>();

        for(DaySign daySign:list){
            calendars.add(new CalendarEvent(daySign.getDate(),daySign.getState()));
        }
        return calendars;
    }

    public void editState(Date date, Integer id) {
        DaySign daySign = daySignDao.findByDate(date,id);
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTime(date);
        int hour = calendar.get(calendar.HOUR_OF_DAY);
        if(hour == 9){
            daySign.setState(1);
        }else if(hour == 8){
            daySign.setState(0);
        }
        saveOrUpdate(daySign);
    }
}