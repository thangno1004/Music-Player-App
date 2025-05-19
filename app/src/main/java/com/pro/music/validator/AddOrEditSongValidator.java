package com.pro.music.validator;
import com.pro.music.utils.StringUtil;

public class AddOrEditSongValidator {
    public enum SongInputError {
        OK,
        NAME_REQUIRED,
        IMAGE_REQUIRED,
        URL_REQUIRED
    }

    public static SongInputError validate(String name, String image, String url) {
        if (StringUtil.isEmpty(name)) return SongInputError.NAME_REQUIRED;
        if (StringUtil.isEmpty(image)) return SongInputError.IMAGE_REQUIRED;
        if (StringUtil.isEmpty(url)) return SongInputError.URL_REQUIRED;
        return SongInputError.OK;
    }
}
