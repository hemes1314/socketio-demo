package com.datacanvas.aps.pipes.api.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;

public class CacheManager {
    private Date saveTime = new Date(0);
    private static  Logger logger = LoggerFactory.getLogger(CacheManager.class);

    public static Boolean isCacheValidate(Date saveTime,int calendarUnit,int userCacheExpireTime){
        Calendar expired = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        expired.setTime(saveTime);
        expired.add(calendarUnit,userCacheExpireTime);
        //logger.info(now + "|" + expired);
        //logger.info("+====================>>: "+ now.before(expired));
        return  now.before(expired);
    }


    @Override
    public String toString() {
        return "CacheManager{" +
                "saveTime=" + saveTime +
                '}';
    }

    public Date getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(Date saveTime) {
        this.saveTime = saveTime;
    }
}
