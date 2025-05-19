package com.pro.music.validator;
import com.pro.music.utils.StringUtil;

public class AdminChangePasswordValidator {
    public enum ChangePasswordError {
        OK,
        OLD_PASSWORD_EMPTY,
        NEW_PASSWORD_EMPTY,
        CONFIRM_PASSWORD_EMPTY,
        OLD_PASSWORD_INCORRECT,
        PASSWORD_MISMATCH,
        PASSWORD_SAME_AS_OLD
    }

    /**
     * Hàm kiểm tra logic thay đổi mật khẩu.
     * @param oldPass mật khẩu cũ do người dùng nhập
     * @param newPass mật khẩu mới do người dùng nhập
     * @param confirmPass mật khẩu xác nhận
     * @param actualOldPassword mật khẩu cũ thực tế trong hệ thống
     * @return kết quả kiểm tra
     */
    public static ChangePasswordError validateChangePassword(String oldPass, String newPass, String confirmPass, String actualOldPassword) {
        if (StringUtil.isEmpty(oldPass)) return ChangePasswordError.OLD_PASSWORD_EMPTY;
        if (StringUtil.isEmpty(newPass)) return ChangePasswordError.NEW_PASSWORD_EMPTY;
        if (StringUtil.isEmpty(confirmPass)) return ChangePasswordError.CONFIRM_PASSWORD_EMPTY;
        if (!oldPass.equals(actualOldPassword)) return ChangePasswordError.OLD_PASSWORD_INCORRECT;
        if (!newPass.equals(confirmPass)) return ChangePasswordError.PASSWORD_MISMATCH;
        if (oldPass.equals(newPass)) return ChangePasswordError.PASSWORD_SAME_AS_OLD;
        return ChangePasswordError.OK;
    }
}
