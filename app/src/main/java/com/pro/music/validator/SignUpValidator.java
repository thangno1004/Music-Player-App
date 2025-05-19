package com.pro.music.validator;

import com.pro.music.constant.Constant;
import com.pro.music.utils.StringUtil;

public class SignUpValidator {

    public enum SignUpError {
        EMAIL_REQUIRED, PASSWORD_REQUIRED, EMAIL_INVALID,
        EMAIL_INVALID_FOR_ADMIN, EMAIL_INVALID_FOR_USER, OK
    }

    public static SignUpError validateInput(String email, String password, boolean isAdmin) {
        if (StringUtil.isEmpty(email)) {
            return SignUpError.EMAIL_REQUIRED;
        } else if (StringUtil.isEmpty(password)) {
            return SignUpError.PASSWORD_REQUIRED;
        } else if (!StringUtil.isValidEmail(email)) {
            return SignUpError.EMAIL_INVALID;
        } else {
            if (isAdmin && !email.contains(Constant.ADMIN_EMAIL_FORMAT)) {
                return SignUpError.EMAIL_INVALID_FOR_ADMIN;
            }
            if (!isAdmin && email.contains(Constant.ADMIN_EMAIL_FORMAT)) {
                return SignUpError.EMAIL_INVALID_FOR_USER;
            }
        }
        return SignUpError.OK;
    }
}