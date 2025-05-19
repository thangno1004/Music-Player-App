package com.pro.music.validator;
import com.pro.music.utils.StringUtil;

public class ForgotPasswordValidator {
    public enum ResetPasswordError {
        EMAIL_REQUIRED,
        EMAIL_INVALID,
        OK
    }

    public static ResetPasswordError validateEmail(String email) {
        if (StringUtil.isEmpty(email)) return ResetPasswordError.EMAIL_REQUIRED;
        if (!StringUtil.isValidEmail(email)) return ResetPasswordError.EMAIL_INVALID;
        return ResetPasswordError.OK;
    }
}
