package com.codepath.simpletodo.util;

import com.codepath.simpletodo.Constants;

import java.util.Date;

/**
 * Created by weslly on 01/03/17.
 */

public class Formatter {
    private static final String[] priorits = {Constants.PRIORITY_LOW, Constants.PRIORITY_MEDIUM, Constants.PRIORITY_HIGHT};


    public static boolean handleBooleans(int val) {
        return val == 1 ? true : false;
    }

    public static int handleBooleans(boolean val) {
        return val ? 1 : 0;
    }

    public static String handlePrioritys(int prior) {
        return priorits[prior];
    }

    public static int handlePrioritys(String prior) {
        for (int i = 0; i < priorits.length; i++) {
            if (prior.equals(priorits[i])) {
                return i;
            }
        }
        return 1; //default medium
    }

    public static long handleDates(Date date) {
        return date.getTime();
    }

    public static Date handleDates(long date) {
        return new Date(date);
    }
}
