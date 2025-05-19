package com.pro.music.validator;

import static org.junit.Assert.*;

import org.junit.Test;

public class AddOrEditSongValidatorTest {

    /**
     * Trường hợp kiểm tra: Tên bài hát bị để trống.
     * Kỳ vọng: Hàm validate() trả về lỗi NAME_REQUIRED vì tên là bắt buộc.
     */
    @Test
    public void testEmptyName_shouldReturnNAME_REQUIRED() {
        AddOrEditSongValidator.SongInputError result = AddOrEditSongValidator.validate("", "image.jpg", "song.mp3");
        assertEquals(AddOrEditSongValidator.SongInputError.NAME_REQUIRED, result);
    }

    /**
     * Trường hợp kiểm tra: Đường dẫn ảnh bài hát bị để trống.
     * Kỳ vọng: Hàm validate() trả về lỗi IMAGE_REQUIRED vì ảnh là bắt buộc.
     */
    @Test
    public void testEmptyImage_shouldReturnIMAGE_REQUIRED() {
        AddOrEditSongValidator.SongInputError result = AddOrEditSongValidator.validate("My Song", "", "song.mp3");
        assertEquals(AddOrEditSongValidator.SongInputError.IMAGE_REQUIRED, result);
    }

    /**
     * Trường hợp kiểm tra: Đường dẫn file nhạc (URL) bị để trống.
     * Kỳ vọng: Hàm validate() trả về lỗi URL_REQUIRED vì liên kết bài hát là bắt buộc.
     */
    @Test
    public void testEmptyUrl_shouldReturnURL_REQUIRED() {
        AddOrEditSongValidator.SongInputError result = AddOrEditSongValidator.validate("My Song", "image.jpg", "");
        assertEquals(AddOrEditSongValidator.SongInputError.URL_REQUIRED, result);
    }

    /**
     * Trường hợp kiểm tra: Tất cả thông tin bài hát hợp lệ.
     * Kỳ vọng: Hàm validate() trả về OK vì dữ liệu nhập đầy đủ và hợp lệ.
     */
    @Test
    public void testValidInput_shouldReturnOK() {
        AddOrEditSongValidator.SongInputError result = AddOrEditSongValidator.validate("My Song", "image.jpg", "song.mp3");
        assertEquals(AddOrEditSongValidator.SongInputError.OK, result);
    }
}