package com.lab.serverclassify.utils;

import org.apache.commons.httpclient.util.DateUtil;

import java.util.Date;

/**
 * @author weixun
 */
public class TimeUtils {

    public static String getNowTime() {
        return DateUtil.formatDate(new Date(), "yyyy-MM-dd|HH_mm_ss_S");
    }
}
