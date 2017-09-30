package com.hr.ent.utils;

/**
 * Created by wdr on 2017/9/27.
 */

public class StringTimeToDateUtils {
    /**
     * 将unix时间戳转换为date类型
     * @param timestampString
     * @param formats
     * @return
     */
    public static String TimeStampDate(String timestampString, String formats){
        Long timestamp = Long.parseLong(timestampString)*1000;
        String date = new java.text.SimpleDateFormat(formats).format(new java.util.Date(timestamp));
        return date;
    }
}
