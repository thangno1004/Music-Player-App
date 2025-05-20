package com.pro.music.validator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class addOrEditCategoryValidatorTest {

    /**
     * Trường hợp: Tên danh mục bị bỏ trống.
     * Kỳ vọng: Trả về NAME_REQUIRED.
     */
    @Test
    public void testEmptyName_shouldReturnNAME_REQUIRED() {
        addOrEditCategoryValidator.CategoryInputError result =
                addOrEditCategoryValidator.validate("", "image.jpg");
        assertEquals(addOrEditCategoryValidator.CategoryInputError.NAME_REQUIRED, result);
    }

    /**
     * Trường hợp: Ảnh danh mục bị bỏ trống.
     * Kỳ vọng: Trả về IMAGE_REQUIRED.
     */
    @Test
    public void testEmptyImage_shouldReturnIMAGE_REQUIRED() {
        addOrEditCategoryValidator.CategoryInputError result =
                addOrEditCategoryValidator.validate("Pop Music", "");
        assertEquals(addOrEditCategoryValidator.CategoryInputError.IMAGE_REQUIRED, result);
    }

    /**
     * Trường hợp: Dữ liệu đầy đủ, hợp lệ.
     * Kỳ vọng: Trả về OK.
     */
    @Test
    public void testValidInput_shouldReturnOK() {
        addOrEditCategoryValidator.CategoryInputError result =
                addOrEditCategoryValidator.validate("Pop Music", "image.jpg");
        assertEquals(addOrEditCategoryValidator.CategoryInputError.OK, result);
    }
}
