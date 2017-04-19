package com.codepath.simpletodo.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by weslly on 01/03/17.
 */

public class Formatter {

    public static boolean handleBooleans(int val) {
        return val == 1 ? true : false;
    }

    public static int handleBooleans(boolean val) {
        return val ? 1 : 0;
    }

    public static long handleDates(Date date) {
        return date.getTime();
    }

    public static Date handleDates(long date) {
        return new Date(date);
    }

    public static String formatterDate(Date date) {
        String toReturn = null;
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm", Locale.getDefault());
            toReturn = dateFormat.format(date);
        }
        return toReturn;
    }
}
