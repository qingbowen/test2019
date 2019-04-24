package com.zowoyoo.kintetsu.tools;


import java.util.Calendar;

public class DateUtils {

    /**
     * 返回当前日期, 格式：yyyy-mm-dd ;
     */
    public static String getToday() {
        Calendar rightNow = Calendar.getInstance();
        int year = rightNow.get(Calendar.YEAR);
        int month = rightNow.get(Calendar.MONTH) + 1;
        int day = rightNow.get(Calendar.DAY_OF_MONTH);
        return year + "-" + (month < 10 ? "0" + month : month + "") + "-" + (day < 10 ? "0" + day : day + "");
    }


}
