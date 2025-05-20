package com.pro.music.validator;

import com.pro.music.utils.StringUtil;

public class addOrEditCategoryValidator {
        public enum CategoryInputError {
            OK,
            NAME_REQUIRED,
            IMAGE_REQUIRED
        }

        public static CategoryInputError validate(String name, String image) {
            if (StringUtil.isEmpty(name)) return CategoryInputError.NAME_REQUIRED;
            if (StringUtil.isEmpty(image)) return CategoryInputError.IMAGE_REQUIRED;
            return CategoryInputError.OK;
        }
}
