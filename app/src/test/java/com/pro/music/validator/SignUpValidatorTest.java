package com.pro.music.validator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SignUpValidatorTest {

    /**
     * Trường hợp: Email rỗng.
     * Giả lập người dùng chọn vai trò User.
     * Kỳ vọng: Trả về lỗi EMAIL_REQUIRED.
     */
    @Test
    public void testEmailEmpty() {
        SignUpValidator.SignUpError result = SignUpValidator.validateInput("", "123456", false);
        assertEquals(SignUpValidator.SignUpError.EMAIL_REQUIRED, result);
    }

    /**
     * Trường hợp: Password rỗng.
     * Giả lập người dùng chọn vai trò User.
     * Kỳ vọng: Trả về lỗi PASSWORD_REQUIRED.
     */
    @Test
    public void testPasswordEmpty() {
        SignUpValidator.SignUpError result = SignUpValidator.validateInput("user@gmail.com", "", false);
        assertEquals(SignUpValidator.SignUpError.PASSWORD_REQUIRED, result);
    }

    /**
     * Trường hợp: Email không đúng định dạng chuẩn.
     * Giả lập vai trò User.
     * Kỳ vọng: Trả về lỗi EMAIL_INVALID.
     */
    @Test
    public void testEmailInvalid() {
        SignUpValidator.SignUpError result = SignUpValidator.validateInput("abc", "123456", false);
        assertEquals(SignUpValidator.SignUpError.EMAIL_INVALID, result);
    }

    /**
     * Trường hợp: Người dùng chọn vai trò Admin,
     * nhưng lại nhập email không chứa định dạng dành cho Admin.
     * Kỳ vọng: Trả về lỗi EMAIL_INVALID_FOR_ADMIN.
     */
    @Test
    public void testAdminEmailWithUserFormat() {
        SignUpValidator.SignUpError result = SignUpValidator.validateInput("user@gmail.com", "123456", true);
        assertEquals(SignUpValidator.SignUpError.EMAIL_INVALID_FOR_ADMIN, result);
    }

    /**
     * Trường hợp: Người dùng chọn vai trò User,
     * nhưng lại nhập email chứa định dạng dành cho Admin.
     * Kỳ vọng: Trả về lỗi EMAIL_INVALID_FOR_USER.
     */
    @Test
    public void testUserEmailWithAdminFormat() {
        SignUpValidator.SignUpError result = SignUpValidator.validateInput("admin@admin.com", "123456", false);
        assertEquals(SignUpValidator.SignUpError.EMAIL_INVALID_FOR_USER, result);
    }

    /**
     * Trường hợp: Người dùng chọn vai trò Admin,
     * và nhập email đúng định dạng Admin.
     * Kỳ vọng: Trả về OK.
     */
    @Test
    public void testValidAdminEmail() {
        SignUpValidator.SignUpError result = SignUpValidator.validateInput("admin@admin.com", "123456", true);
        assertEquals(SignUpValidator.SignUpError.OK, result);
    }

    /**
     * Trường hợp: Người dùng chọn vai trò User,
     * và nhập email đúng định dạng User.
     * Kỳ vọng: Trả về OK.
     */
    @Test
    public void testValidUserEmail() {
        SignUpValidator.SignUpError result = SignUpValidator.validateInput("user@gmail.com", "123456", false);
        assertEquals(SignUpValidator.SignUpError.OK, result);
    }
}
