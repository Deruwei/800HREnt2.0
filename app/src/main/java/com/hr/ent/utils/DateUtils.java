package com.hr.ent.utils;

import android.annotation.SuppressLint;
import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author james
 */
public abstract class DateUtils {

    public static String dateRange(Date time) {
        return dateRange(new Date(), time);
    }

    public static String dateRange(Date startTime, Date endTime) {
        String strTime = null;
        if (startTime != null && endTime != null) {
            int diff = (int) ((startTime.getTime() - endTime.getTime()) / 1000);

            if (diff <= 60) {
                strTime = "当前";
            } else if (diff <= 60 * 60) {
                strTime = diff / 60 + "分钟前";
            } else if (diff <= 60 * 60 * 24) {
                strTime = diff / (60 * 60) + "小时前";
            } else if (diff <= 60 * 60 * 24 * 30) {
                strTime = diff / (60 * 60 * 24) + "天前";
            } else {
                strTime = "一个月前";
            }
        }

        return strTime;
    }

    /**
     * @return 返回yyyy-MM-dd HH:mm:ss格式的日期
     */
    public static String transforMillToDateInfo(long dateline) {
        String content = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        // 前面的dateline是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
        java.util.Date dt = new Date(dateline * 1000);
        content = sdf.format(dt); // 得到精确到秒的表示：2006-08-31 21:08:00
        return content;
    }


    /**
     * @return 返回yyyy-MM-dd HH:mm:ss格式的日期
     */
    public static String transforMillToDateInfoService(long dateline) {
        String content = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        // 前面的dateline是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
        java.util.Date dt = new Date(dateline * 1000);
        content = sdf.format(dt); // 得到精确到秒的表示：2006-08-31 21:08:00
        return content;
    }

    public static CharSequence format(Date date) {
        return DateFormat.format("yyyy-MM-dd h:mm:ss", date);
    }

    /**
     * 将时间字符串格式化为毫秒
     *
     * @param date
     * @return
     */
    public static long transforDateToMill(String date) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date startTime = sdf.parse(date);
            return startTime.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * 获取当天的日期   年-月-日
     *
     * @return
     */
    public static final String getDate() {
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH) + 1;
        String parse = c.get(Calendar.YEAR) + "-" + month + "-" + c.get(Calendar.DAY_OF_MONTH);
        return parse;
    }

    /**
     * 获取当天的年份
     *
     * @return
     */
    public static final int getYearDate() {
        Calendar c = Calendar.getInstance();
        int parse = c.get(Calendar.YEAR);
        return parse;
    }

    /**
     * 将时间毫秒格式化为字符串
     *
     * @param mill
     * @return
     */
    public static String transforMillToDate(long mill) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

        Date date = new Date(mill);

        return sdf.format(date);
    }

    public static String transforMillToDate2(long mill) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

        Date date = new Date(mill);

        return sdf.format(date);
    }

    public static final Integer Str2Second(String s) {
        try {
            int sum = 0;
            String[] ss = s.split("[:：]");

            sum += Integer.parseInt(StringUtils.trimLeadingWhitespace(ss[0])) * 3600;
            sum += Integer.parseInt(StringUtils.trimLeadingWhitespace(ss[1])) * 60;

            if (ss.length == 3) {
                sum += Integer.parseInt(StringUtils.trimLeadingWhitespace(ss[2]));
            }
            return sum;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressLint("SimpleDateFormat")
    public static String GetNowDateChinesne() {
        String temp_str = "";
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        temp_str = sdf.format(dt);
        return temp_str;
    }

    @SuppressLint("SimpleDateFormat")
    public static String GetPevDateChinesne() {
        String temp_str = "";
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        temp_str = sdf.format(dt.getTime() - 24 * 60 * 60 * 1000);
        return temp_str;
    }

    @SuppressLint("SimpleDateFormat")
    public static String tranforDatelineToString(long dateline) {
        String temp_str = "";
        Date dt = new Date(dateline * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        temp_str = sdf.format(dt);
        return temp_str;
    }

}
