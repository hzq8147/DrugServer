package com.drug.utils;

import java.sql.Timestamp;
import java.util.Date;

public class TimeUtils {
    public static Timestamp getNowTime(){
        Date date = new Date();
        Timestamp nowTime = new Timestamp(date.getTime());
        return nowTime;
    }
}
