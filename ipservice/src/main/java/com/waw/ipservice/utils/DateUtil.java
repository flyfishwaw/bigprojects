package com.waw.ipservice.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author flyfish
 * @date 2020-09-01 10:09:02
 * @description
 */
public class DateUtil {

    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String formatDate(Date date) {
        return dateFormat.format(date);
    }
}
