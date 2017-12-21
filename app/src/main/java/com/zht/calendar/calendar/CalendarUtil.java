package com.zht.calendar.calendar;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者：zhanghaitao on 2017/12/21 13:31
 * 邮箱：820159571@qq.com
 *
 * @describe:计算日历的工具类
 */

public class CalendarUtil {


    //记录当前月份前几个月的总天数(不是闰年)
    static int[] totalDaysBeforeCurrentMonth = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
    //每个月份对应的天数
    static int[] daysOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private int year;//年
    private int month;//月
    private int day;//日

    private boolean isLeapYear;//当前年份是都是闰年
    private int FebruaryDays;//当前二月的天数
    private int weekDay;//星期几

    public int MONDAY_FIRST = 0;
    public int SUNDAY_FIRST = 1;


    /**
     * 获取当前日期
     */
    public String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        String[] currentDates = str.split("-");
        return str;
    }

    /**
     * 判断是否是闰年
     * 闰年的两个条件：
     * 1、能被4整除
     * 2、能被100整除的年份，同时又能被400整除的年份
     */
    public static boolean isLeapYear(int year) {
        //判断当前年份是不是闰年
        //能被100整除的年份，同时又能被400整除的年份
//        if (year % 100 == 0) {//拦截年份是100的整数的年份
//            return year % 400 == 0;
//        } else {//能被4整除
//            return year % 4 == 0;
//        }
        return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);

    }

    /**
     * 获取当年之前一共有多少个闰年
     */
    public static int getLeapYears(int year) {
        int leapYears = 0;
        for (int i = 1; i < year; i++) {
            if (isLeapYear(i)) {
                leapYears++;
            }
        }
        return leapYears;
    }


    /**
     * 获取当月的天数
     */
    public static int getMonthDays(int year, int month) {
        if (isLeapYear(year)) {
            return month == 2 ? 29 : daysOfMonth[month - 1];
        } else {
            return daysOfMonth[month - 1];
        }

    }

    /**
     * 获取当年的当月之前的总天数（从1月算）
     */
    public static int getBeforeMonthDays(int year, int month) {
        int day = totalDaysBeforeCurrentMonth[month - 1];
        if (isLeapYear(year)) {
            return month > 2 ? day + 1 : day;
        } else {
            return day;
        }
    }


    /**
     * 获取当月之前的总天数（从0年0月0日算，0年1月1日---是星期日，在西方星期日才是一个星期的第一天）
     */
    public static int getTotalDaysBeforeMonth(int year, int month) {
        int leapYears = getLeapYears(year);//之前多少个闰年
        int monthDay = getBeforeMonthDays(year, month);//month之前有多少天
        int totalDays;
        totalDays = 365 * (year - 1);//除去闰年的二月的一天，一共有多少天
        totalDays = totalDays + leapYears + monthDay;
        return totalDays;
    }


    /**
     * 获取总天数
     */
    public static int getTotalDays(int year, int month, int day) {
        return getTotalDaysBeforeMonth(year, month) + day;
    }

    /**
     * 获取当前日期是星期几
     */
    public static int getWeekDay(int year, int month, int day) {
        return getTotalDays(year, month, day) % 7;
    }


    /**
     * 获取当前月的第一天是星期几
     */
    public static int getFirstDayWeek(int year, int month) {
       int firatDay =  getTotalDaysBeforeMonth(year, month) +1;
        return firatDay%7;
    }










    ///////////////////////////////////娱乐方法（不可用）

    /**
     * 蔡勒（Zeller）公式，是一个计算星期的公式，随便给一个日期，就能用这个公式推算出是星期几。
     * 蔡勒公式只适合于1582年（中国明朝万历十年）10月15日之后的日历。
     * <p>
     * 1、W=[C/4]-2*C+y+[y/4]+[26*(m+1)/10]+d-1 （其中[ ]为取整符号）
     * <p>
     * W是所求日期的星期数.
     * 如果求得的数大于7,可以减去7的倍数,直到余数小于7为止.
     * c是公元年份的前两位数字,
     * y是已知公元年份的后两位数字;
     * m是月数,
     * d是日数.
     * 方括[ ]表示只截取该数的整数部分。
     * 2、还有一个特别要注意的地方:所求的月份如果是1月或2月,则应视为前一年的13月或14月.
     * 所以公式中m 的取值范围不是1-12,而是3-14.
     * 1--->13
     * 2--->14
     */

   /* public static int getWeekDay(int year, int month, int day) {
        int C = year / 100;
        int Y = year % 100;
        int week = (C / 4) - 2 * C + Y + (Y / 4) + (26 * (month + 1) / 10) + day - 1;
        return week % 7;
    }*/


}
