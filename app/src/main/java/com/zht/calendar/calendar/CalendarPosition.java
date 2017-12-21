package com.zht.calendar.calendar;

import android.app.Activity;
import android.util.DisplayMetrics;

import com.zht.calendar.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：zhanghaitao on 2017/12/21 15:36
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class CalendarPosition {


    private int Height = R.dimen.view_height;


    char[] weekDays = {'日', '一', '二', '三', '四', '五', '六'};

    /**
     * 星期一开头
     * <p>
     * '一', '二', '三', '四', '五', '六','日'
     */
    public static List<String> getPosition(int year, int month) {

        //当月第一天星期几
        int firstDayWeek = CalendarUtil.getFirstDayWeek(year, month);
        //下一个月第一天星期几
        int nextMonthDayWeek;
        if (month == 12) {
            nextMonthDayWeek = CalendarUtil.getFirstDayWeek(year + 1, 1);
        } else {
            nextMonthDayWeek = CalendarUtil.getFirstDayWeek(year, month + 1);
        }
        //前一个月有多少天
        int previousMonthDays;
        if (month == 1) {
            previousMonthDays = CalendarUtil.getMonthDays(year - 1, 12);
        } else {
            previousMonthDays = CalendarUtil.getMonthDays(year, month - 1);
        }
        //当月有多少天
        int monthDays = CalendarUtil.getMonthDays(year, month);

        boolean hasPrevious = firstDayWeek != 1;
        boolean hasNext = nextMonthDayWeek != 1;

        List<String> position = new ArrayList<>();

        if (hasPrevious) {
            int startDay;
            if (firstDayWeek == 0) {
                startDay = previousMonthDays - 5;
            } else {
                startDay = previousMonthDays - firstDayWeek + 1;
            }
            for (int i = 0; i < (firstDayWeek == 0 ? 6 : firstDayWeek); i++) {
                position.add(String.valueOf((startDay)));
                startDay++;
            }
        }

        for (int i = 0; i < monthDays; i++) {
            position.add(String.valueOf((i + 1)));
        }

        if (hasNext) {
            int endDay;
            if (nextMonthDayWeek == 0) {
                endDay = 1;
            } else {
                endDay = 8 - nextMonthDayWeek;
            }
            for (int i = 0; i < endDay; i++) {

                position.add(String.valueOf((i + 1)));
            }
        }

        return position;
    }

    /**
     * 星期日开头
     */
    public static float getPosition2(Activity activity, int year, int month) {
        float width = getWidth(activity);

        //当月第一天星期几
        int firstDayWeek = CalendarUtil.getFirstDayWeek(year, month);
        //下一个月第一天星期几
        int nextMonthDayWeek = CalendarUtil.getFirstDayWeek(year, month - 1);
        //当月第最后一天星期几
        int lastDayWeek = nextMonthDayWeek == 0 ? 6 : nextMonthDayWeek - 1;
        //前一个月有多少天
        int previousMonthDays = CalendarUtil.getMonthDays(year, month - 1);
        //当月有多少天
        int monthDays = CalendarUtil.getMonthDays(year, month);


        return (float) 0.0;
    }


    /**
     * 获取屏幕宽度
     */
    public static float getWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        // 取得窗口属性
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        // 窗口的宽度
        return dm.widthPixels;
    }


    private static class Cell {

        private static int PREVIOUS = 0;
        private static int CURRENT = 1;
        private static int NEXT = 2;

        private int type; //0:当前  1:上个月 2:下个月
        private String week;//星期几
        private String year;
        private String month;
        private String day;
        private int row;//行
        private int column;//列

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getColumn() {
            return column;
        }

        public void setColumn(int column) {
            this.column = column;
        }
    }


}
