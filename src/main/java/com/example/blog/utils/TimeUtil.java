package com.example.blog.utils;

import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author: zhangocean
 * @Date: 2018/6/20 19:55
 * Describe: 时间工具
 */
public class TimeUtil {

    /**
     * 格式化日期
     * 使用线程安全的DateTimeFormatter
     *
     * @return “年-月-日”字符串
     */
    public String getFormatDateForThree() {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return now.format(format);
    }

    /**
     * 格式化日期
     * 使用线程安全的DateTimeFormatter
     *
     * @return “年-月-日 时:分:秒”字符串
     */
    public static String getFormatDateForSix() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(format);
    }

    /**
     * 格式化日期
     * 使用线程安全的DateTimeFormatter
     *
     * @return “年-月-日 时:分”字符串
     */
    public String getFormatDateForFive() {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return now.format(format);
    }

    public static String getFormatDateForFiveNum() {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return now.format(format);
    }

    /**
     * 解析日期
     *
     * @param date 日期 2018-06-21
     * @return
     */
    public LocalDate getParseDateForThree(String date) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, format);
    }

    /**
     * 解析日期
     *
     * @param date 日期 2018-06-21 12:01:25
     * @return
     */
    public LocalDate getParseDateForSix(String date) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDate.parse(date, format);
    }

    /**
     * 获得当前时间的时间戳
     *
     * @return 时间戳
     */
    public long getLongTime() {
        Date now = new Date();
        return now.getTime() / 1000;
    }

    /**
     * 时间中横杆转换为年
     *
     * @param str 2018-08
     * @return 2018年8月
     */
    public String timeWhippletreeToYear(String str) {
        StringBuilder s = new StringBuilder();
        s.append(str.substring(0, 4));
        s.append("年");
        s.append(str.substring(5, 7));
        s.append("月");
        return String.valueOf(s);
    }

    /**
     * 时间中的年转换为横杠
     *
     * @param str 2018年07月
     * @return 2018-07
     */
    public String timeYearToWhippletree(String str) {
        StringBuilder s = new StringBuilder();
        s.append(str.substring(0, 4));
        s.append("-");
        s.append(str.substring(5, 7));
        return String.valueOf(s);
    }

    public static String UTCToUTC(String UTCStr) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        Date date = null;
        try {
            date = sdf1.parse(UTCStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + 8);
        Date date11 = calendar.getTime();
        String format = sdf2.format(date11);
        return format;
    }


    public static void main(String[] args) throws ParseException {
        UTCToUTC("2018-02-05T13:06:22Z");
        System.out.println(getFormatDateForFiveNum());
        System.out.println(System.currentTimeMillis());
        System.out.println(getFormatDateForSix());
    }
}
