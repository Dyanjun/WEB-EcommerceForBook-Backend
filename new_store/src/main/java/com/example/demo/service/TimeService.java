package com.example.demo.service;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Service
public class TimeService {

    public String getTime() {
        System.out.println("获取时间" + new Date());

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");
        System.out.println("格式化输出：" + sdf.format(d));

        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        System.out.println("Asia/Shanghai:" + sdf.format(d));

        return sdf.format(d);
    }
}
