package com.pro.music.validator;
import com.pro.music.constant.Constant;
import com.pro.music.utils.StringUtil;
public class SignInValidator {
    public enum SignInError {
        OK,
        EMAIL_REQUIRED,
        PASSWORD_REQUIRED,
        EMAIL_INVALID,
        EMAIL_INVALID_FOR_ADMIN,
        EMAIL_INVALID_FOR_USER
    }
    public static SignInError validate(String email, String password, boolean isAdminChecked) {
        if (StringUtil.isEmpty(email)) return SignInError.EMAIL_REQUIRED;
        if (StringUtil.isEmpty(password)) return SignInError.PASSWORD_REQUIRED;
        if (!StringUtil.isValidEmail(email)) return SignInError.EMAIL_INVALID;

        if (isAdminChecked) {
            if (!email.contains(Constant.ADMIN_EMAIL_FORMAT)) return SignInError.EMAIL_INVALID_FOR_ADMIN;
        } else {
            if (email.contains(Constant.ADMIN_EMAIL_FORMAT)) return SignInError.EMAIL_INVALID_FOR_USER;
        }

        return SignInError.OK;
    }
}
