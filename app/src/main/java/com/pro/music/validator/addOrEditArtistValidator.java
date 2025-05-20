package com.pro.music.validator;

import com.pro.music.utils.StringUtil;

public class addOrEditArtistValidator {
    public enum ArtistInputError {
        OK,
        NAME_REQUIRED,
        IMAGE_REQUIRED
    }

    public static ArtistInputError validate(String name, String image) {
        if (StringUtil.isEmpty(name)) return ArtistInputError.NAME_REQUIRED;
        if (StringUtil.isEmpty(image)) return ArtistInputError.IMAGE_REQUIRED;
        return ArtistInputError.OK;
    }
}
