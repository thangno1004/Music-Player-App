package com.pro.music.validator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SignInValidatorTest {

    /**
     * TC 2.1
     * Trường hợp: Email rỗng.
     * Vai trò: user (isAdmin = false).
     * Kỳ vọng: Trả về lỗi EMAIL_REQUIRED.
     */
    @Test
    public void testEmailEmpty() {
        SignInValidator.SignInError result = SignInValidator.validate("", "123456", false);
        assertEquals(SignInValidator.SignInError.EMAIL_REQUIRED, result);
    }

    /**
     * TC 2.2
     * Trường hợp: Password rỗng.
     * Vai trò: user.
     * Kỳ vọng: Trả về lỗi PASSWORD_REQUIRED.
     */
    @Test
    public void testPasswordEmpty() {
        SignInValidator.SignInError result = SignInValidator.validate("user@gmail.com", "", false);
        assertEquals(SignInValidator.SignInError.PASSWORD_REQUIRED, result);
    }

    /**
     * TC 2.3
     * Trường hợp: Email không đúng định dạng chuẩn (không chứa @).
     * Vai trò: user.
     * Kỳ vọng: Trả về lỗi EMAIL_INVALID.
     */
    @Test
    public void testEmailInvalidFormat() {
        SignInValidator.SignInError result = SignInValidator.validate("invalid-email", "123456", false);
        assertEquals(SignInValidator.SignInError.EMAIL_INVALID, result);
    }

    /**
     * TC 2.4
     * Trường hợp: Người dùng chọn vai trò admin,
     * nhưng email lại không chứa định dạng dành cho admin.
     * Kỳ vọng: Trả về lỗi EMAIL_INVALID_FOR_ADMIN.
     */
    @Test
    public void testAdminEmailWithUserFormat() {
        SignInValidator.SignInError result = SignInValidator.validate("user@gmail.com", "123456", true);
        assertEquals(SignInValidator.SignInError.EMAIL_INVALID_FOR_ADMIN, result);
    }

    /**
     * TC 2.5
     * Trường hợp: Người dùng chọn vai trò user,
     * nhưng email lại chứa định dạng dành cho admin.
     * Kỳ vọng: Trả về lỗi EMAIL_INVALID_FOR_USER.
     */
    @Test
    public void testUserEmailWithAdminFormat() {
        SignInValidator.SignInError result = SignInValidator.validate("admin@admin.com", "123456", false);
        assertEquals(SignInValidator.SignInError.EMAIL_INVALID_FOR_USER, result);
    }

    /**
     * TC 2.6
     * Trường hợp: Người dùng chọn vai trò admin,
     * và nhập email hợp lệ dành cho admin.
     * Kỳ vọng: Trả về OK.
     */
    @Test
    public void testValidAdmin() {
        SignInValidator.SignInError result = SignInValidator.validate("admin@admin.com", "123456", true);
        assertEquals(SignInValidator.SignInError.OK, result);
    }

    /**
     * TC 2.7
     * Trường hợp: Người dùng chọn vai trò user,
     * và nhập email hợp lệ dành cho user.
     * Kỳ vọng: Trả về OK.
     */
    @Test
    public void testValidUser() {
        SignInValidator.SignInError result = SignInValidator.validate("user@gmail.com", "123456", false);
        assertEquals(SignInValidator.SignInError.OK, result);
    }
}
