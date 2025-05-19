package com.pro.music.validator;

import com.pro.music.validator.ForgotPasswordValidator;
import com.pro.music.validator.ForgotPasswordValidator.ResetPasswordError;
import org.junit.Test;
import static org.junit.Assert.*;

public class ForgotPasswordValidatorTest {

    /**
     * Trường hợp kiểm tra: Email rỗng.
     * Mong đợi: Hàm trả về lỗi EMAIL_REQUIRED vì email không được để trống.
     */
    @Test
    public void testEmailEmpty() {
        assertEquals(ResetPasswordError.EMAIL_REQUIRED, ForgotPasswordValidator.validateEmail(""));
    }

    /**
     * Trường hợp kiểm tra: Email sai định dạng (không đúng chuẩn email).
     * Mong đợi: Hàm trả về lỗi EMAIL_INVALID vì không phải định dạng email hợp lệ.
     */
    @Test
    public void testEmailInvalid() {
        assertEquals(ResetPasswordError.EMAIL_INVALID, ForgotPasswordValidator.validateEmail("abc"));
    }

    /**
     * Trường hợp kiểm tra: Email hợp lệ (đúng định dạng).
     * Mong đợi: Hàm trả về OK, nghĩa là dữ liệu đầu vào hợp lệ.
     */
    @Test
    public void testValidUserEmail() {
        assertEquals(ResetPasswordError.OK, ForgotPasswordValidator.validateEmail("user@gmail.com"));
    }
}

