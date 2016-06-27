package com.databasecourse.salary.utils;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * date:2016-05-29 17:31
 */
public class CalendarEvent {
    private String title;
    private Date start;
    private String backgroundColor;
    private String textColor;

    public CalendarEvent() {
    }

    public CalendarEvent(Date start, int type) {
        this.start = start;
        this.textColor = "#FFFFFF";
        if(type == 0){
            this.title = "已签到";
            this.backgroundColor = "#257e4a";
        }

        if(type == 1){
            this.title = "迟到";
            this.backgroundColor = "#D5B32B";
        }

        if(type == 2){
            this.title = "请假";
            this.backgroundColor = "#EE6911";
        }

        if(type == 3){
            this.title = "旷工";
            this.backgroundColor = "red";
        }


        if(type == 9999){
            this.title = "未签到";
            this.backgroundColor = "#808080";
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JSONField(format = "yyyy-MM-dd")
    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    @Override
    public String toString() {
        return "CalcenderEvent{" +
                "title='" + title + '\'' +
                ", start=" + start +
                ", backgroundColor='" + backgroundColor + '\'' +
                ", textColor='" + textColor + '\'' +
                '}';
    }
}
