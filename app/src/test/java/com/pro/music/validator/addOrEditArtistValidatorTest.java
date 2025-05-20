package com.pro.music.validator;

import static org.junit.Assert.*;

import org.junit.Test;

public class addOrEditArtistValidatorTest {

    /**
     * Test case: Tên nghệ sĩ bị bỏ trống.
     * Mục tiêu: Kiểm tra hàm validate() phát hiện thiếu tên nghệ sĩ.
     * Kỳ vọng: Trả về lỗi NAME_REQUIRED.
     */
    @Test
    public void testEmptyName_shouldReturnNAME_REQUIRED() {
        assertEquals(addOrEditArtistValidator.ArtistInputError.NAME_REQUIRED,
                addOrEditArtistValidator.validate("", "img.jpg"));
    }

    /**
     * Test case: Đường dẫn ảnh bị bỏ trống.
     * Mục tiêu: Kiểm tra hàm validate() phát hiện thiếu ảnh đại diện nghệ sĩ.
     * Kỳ vọng: Trả về lỗi IMAGE_REQUIRED.
     */
    @Test
    public void testEmptyImage_shouldReturnIMAGE_REQUIRED() {
        assertEquals(addOrEditArtistValidator.ArtistInputError.IMAGE_REQUIRED,
                addOrEditArtistValidator.validate("Artist", ""));
    }

    /**
     * Test case: Cả tên nghệ sĩ và ảnh đều hợp lệ.
     * Mục tiêu: Kiểm tra hàm validate() nhận diện đầu vào hợp lệ.
     * Kỳ vọng: Trả về OK.
     */
    @Test
    public void testValidInput_shouldReturnOK() {
        assertEquals(addOrEditArtistValidator.ArtistInputError.OK,
                addOrEditArtistValidator.validate("Artist", "img.jpg"));
    }
}
