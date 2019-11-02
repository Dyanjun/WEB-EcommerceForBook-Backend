package com.example.demo.controller;

import com.example.demo.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeController {
    @Autowired
    TimeService timeService;

    @RequestMapping("yeartime")
    public String getYTime(){
        String time=timeService.getTime();
        String [] time1=time.split("-");
        long year=Long.parseLong(time1[0])-1;
        time1[0]=Long.toString(year);

        String timenow=time1[0]+"-"+time1[1]+"-"+time1[2];

        System.out.print(timenow);
        return timenow;
    }

    @RequestMapping("threemonth")
    public String getTTime(){
        String time=timeService.getTime();
        String [] time1=time.split("-");
        long year=Long.parseLong(time1[0]);
        long month=Long.parseLong(time1[1]);
        String timenow;
        if(month>=4){
            month=month-3;
            time1[0]=Long.toString(year);
            time1[1]=Long.toString(month);
            timenow=time1[0]+"-0"+time1[1]+"-"+time1[2];
        }
        else{
            month=month+9;
            year=year-1;
            time1[0]=Long.toString(year);
            time1[1]=Long.toString(month);
            timenow=time1[0]+"-"+time1[1]+"-"+time1[2];
        }




        System.out.print(timenow);
        return timenow;
    }
}

