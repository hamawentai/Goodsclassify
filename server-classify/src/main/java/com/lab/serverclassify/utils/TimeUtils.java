package com.lab.serverclassify.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd|HH-mm-ss-S");

    public static String getNowTime() {
        return format.format(new Date());
    }
}
