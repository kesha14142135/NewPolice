package com.klg.newpolice.util;

import android.util.Base64;

public class StringUtils {

    public static String getBasicAuthHeader(String login, String password) {
        String base = login + ":" + password;
        return "Basic " + Base64.encodeToString(base.getBytes(), Base64.NO_WRAP);
    }
}
