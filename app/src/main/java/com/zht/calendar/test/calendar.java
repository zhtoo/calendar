package com.zht.calendar.test;

import com.zht.calendar.calendar.CalendarPosition;

import java.util.List;
import java.util.Scanner;


/**
 * 作者：zhanghaitao on 2017/12/21 13:10
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class calendar {

    public static void main(String[] args) {

//        int[] daysOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
//            int sum = 0;
//        for (int i = 0; i < daysOfMonth.length; i++) {
//            System.out.println("sum："+i+"---"+sum);
//            sum+=daysOfMonth[i];
//        }
//        System.out.println("sum："+12+"---"+sum);


        long startTime = System.currentTimeMillis();
        System.out.println("方法执行开始时间：" + (startTime));
        //startcalendar();
//        int week = CalendarUtil.getWeekDay(2017, 12, 24);
//        //int week = CalendarUtil.getFirstDayWeekPosition(2017, 12, 21);
//        System.out.println("week=" + week);

        List<String> position = CalendarPosition.getPosition(2017, 12);
        for (int i = 0; i < position.size(); i++) {
            if (i != 1 && i % 7 == 1) {
                System.out.println("");
                System.out.print(position.get(i)+"---");
            }else {
                System.out.print(position.get(i)+"---");
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("方法执行结束时间：" + (endTime));
        System.out.println("方法执行时间：" + (endTime - startTime));

//        int week = CalendarUtil.getWeekDay(2013, 1, 1);
//
//        System.out.println("week=" + week);
    }

    private static void startcalendar() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入您要查询的年 月 日 （以空格 隔开,结束程序输入-1）");
            int x = sc.nextInt();
            if (x == -1) {
                break;
            }
            int y = sc.nextInt();
            if (y == -1) {
                break;
            }
            int z = sc.nextInt();
            if (z == -1) {
                break;
            }
            call(x, y, z);
        }
    }

    public static void call(int year, int month, int day) {
        int isLeapYear = 0,
                LeapYearNumber = 0,
                allDays = 0,
                weekDay = 0,
                daysBeforeMonth = 0,
                monthFirstWeek = 0,
                w = 0;//空格
        //判断当前年份是不是闰年
        if (year % 100 == 0) {
            if (year % 400 == 0) {
                isLeapYear++;
            }
        } else if (year % 4 == 0) {
            isLeapYear++;
        }
        //判断输入是否合法
        //年
        if (year < 0) {
            System.out.println("您输入的年份有误,当前无法计算");
            return;
        }
        //月
        if (month < 0 || month > 12) {
            System.out.println("您输入的月份有误，当前无法计算");
            return;
        }
        //记录当前月份前几个月的总天数（闰年的）
        int[] arr1 = {0, 31, 29, 90, 120, 151, 181, 212, 243, 273, 304, 334};
        //每个月份对应的天数
        int[] arr2 = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (day < 0 || day > 31) {
            System.out.println("您输入的天数有误，当前无法计算");
            return;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            if (day == 31) {
                System.out.println("你输入的月份没有31天,无法计算");
                return;
            }
        } else if (month == 2) {
            if (isLeapYear == 1) {
                if (day > 29) {
                    System.out.println("你输入的月份没有" + day + "天，无法计算");
                    return;
                }
            } else if (day > 28) {
                System.out.println("你输入的月份没有" + day + "天，无法计算");
                return;
            }
        }

        // 此时a=490。有多少个闰年。

        if ((isLeapYear == 1) & (month == 2)) {
            arr2[(month - 1)]++;
        }
        if ((isLeapYear == 1) & (month > 2)) {
            arr1[(month - 1)]++;
        }
        allDays = LeapYearNumber + (year - 1) * 365 + arr1[(month - 1)] + day;//总天数
        weekDay = allDays % 7;  //计算星期几  7--->0


        char[] arr3 = {'天', '一', '二', '三', '四', '五', '六'};
        System.out.println(year + "年" + month + "月" + day + "号是星期" + arr3[weekDay]);
        daysBeforeMonth = allDays - day;//当月之前有多少天
        monthFirstWeek = daysBeforeMonth % 7 + 1;//当月第一天是星期几

        System.out.println(year + "年" + month + "月的日历如下：");
        System.out.print("----------------------------\n 日\t一\t二\t三\t四\t五\t六 \n----------------------------\n");//打印排头
        for (int p = 1; p <= monthFirstWeek; p++) {
            if (monthFirstWeek != 7) {
                System.out.print(' ' + "\t");
            }
        }
        for (int t = monthFirstWeek + 1; t <= 7; t++) {
            ++w;
            System.out.print(w + "\t");
        }
        if (monthFirstWeek != 7) {
            System.out.println(' ');
        }
        for (int z = w + 1; z <= arr2[(month - 1)]; z = z) {
            for (int q = 0; q < 7; q++) {
                if (z <= arr2[(month - 1)]) {
                    System.out.print(z + "\t");
                    z++;
                }
            }
            System.out.println();
        }
        System.out.println("----------------------------");
        System.out.println();
    }


}
