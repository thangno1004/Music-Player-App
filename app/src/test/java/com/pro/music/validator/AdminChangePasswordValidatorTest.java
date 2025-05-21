package com.pro.music.validator;

import com.pro.music.validator.AdminChangePasswordValidator.ChangePasswordError;
import org.junit.Test;
import static org.junit.Assert.*;


public class AdminChangePasswordValidatorTest {

    /**
     * TC 1.1
     * Trường hợp: Mật khẩu cũ rỗng.
     * Kỳ vọng: Trả về OLD_PASSWORD_EMPTY vì người dùng chưa nhập mật khẩu cũ.
     */
    @Test
    public void testOldPasswordEmpty_shouldReturnOLD_PASSWORD_EMPTY() {
        ChangePasswordError result = AdminChangePasswordValidator.validateChangePassword("", "new123", "new123", "old123");
        assertEquals(ChangePasswordError.OLD_PASSWORD_EMPTY, result);
    }

    /**
     * TC 1.2
     * Trường hợp: Mật khẩu mới rỗng.
     * Kỳ vọng: Trả về NEW_PASSWORD_EMPTY vì mật khẩu mới là bắt buộc.
     */
    @Test
    public void testNewPasswordEmpty_shouldReturnNEW_PASSWORD_EMPTY() {
        ChangePasswordError result = AdminChangePasswordValidator.validateChangePassword("old123", "", "new123", "old123");
        assertEquals(ChangePasswordError.NEW_PASSWORD_EMPTY, result);
    }

    /**
     * TC 1.3
     * Trường hợp: Mật khẩu xác nhận rỗng.
     * Kỳ vọng: Trả về CONFIRM_PASSWORD_EMPTY vì người dùng chưa xác nhận mật khẩu mới.
     */
    @Test
    public void testConfirmPasswordEmpty_shouldReturnCONFIRM_PASSWORD_EMPTY() {
        ChangePasswordError result = AdminChangePasswordValidator.validateChangePassword("old123", "new123", "", "old123");
        assertEquals(ChangePasswordError.CONFIRM_PASSWORD_EMPTY, result);
    }

    /**
     * TC 1.4
     * Trường hợp: Mật khẩu cũ nhập sai so với hệ thống.
     * Kỳ vọng: Trả về OLD_PASSWORD_INCORRECT.
     */
    @Test
    public void testOldPasswordIncorrect_shouldReturnOLD_PASSWORD_INCORRECT() {
        ChangePasswordError result = AdminChangePasswordValidator.validateChangePassword("wrong", "new123", "new123", "old123");
        assertEquals(ChangePasswordError.OLD_PASSWORD_INCORRECT, result);
    }

    /**
     * TC 1.5
     * Trường hợp: Mật khẩu mới và mật khẩu xác nhận không trùng khớp.
     * Kỳ vọng: Trả về PASSWORD_MISMATCH.
     */
    @Test
    public void testPasswordMismatch_shouldReturnPASSWORD_MISMATCH() {
        ChangePasswordError result = AdminChangePasswordValidator.validateChangePassword("old123", "new123", "notmatch", "old123");
        assertEquals(ChangePasswordError.PASSWORD_MISMATCH, result);
    }

    /**
     * TC 1.6
     * Trường hợp: Mật khẩu mới trùng với mật khẩu cũ.
     * Kỳ vọng: Trả về PASSWORD_SAME_AS_OLD.
     */
    @Test
    public void testNewSameAsOld_shouldReturnPASSWORD_SAME_AS_OLD() {
        ChangePasswordError result = AdminChangePasswordValidator.validateChangePassword("samepass", "samepass", "samepass", "samepass");
        assertEquals(ChangePasswordError.PASSWORD_SAME_AS_OLD, result);
    }

    /**
     * TC 1.7
     * Trường hợp: Tất cả đầu vào hợp lệ.
     * Kỳ vọng: Trả về OK.
     */
    @Test
    public void testValidChange_shouldReturnOK() {
        ChangePasswordError result = AdminChangePasswordValidator.validateChangePassword("old123", "new123", "new123", "old123");
        assertEquals(ChangePasswordError.OK, result);
    }
}
