package com.databasecourse.salary.tests;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * date:2016-05-26 15:32
 */
public class JUnitTests {
    @Test
    public void tttt() throws ParseException {
      /*  String editInfo = "[{\"id\":\"5\",\"state0\":1,\"state1\":1,\"state2\":1,\"state3\":1}]";
        List<Map<String, Object>>lists= JSON.parseObject(editInfo,new TypeReference<List<Map<String,Object>>>(){});
        Map obj = lists.get(0);
        System.out.println(obj.get("state1"));*/


        String str = "2013-05";
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat dateFormatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = dateFormatter.parse(str);

        System.out.println(dateFormatter1.format(date));
    }
}
