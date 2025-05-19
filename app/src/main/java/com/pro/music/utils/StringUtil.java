package com.pro.music.utils;

import java.util.regex.Pattern;

public class StringUtil {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$",
            Pattern.CASE_INSENSITIVE
    );

    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}

