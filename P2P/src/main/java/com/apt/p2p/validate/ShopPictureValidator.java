package com.apt.p2p.validate;

import com.apt.p2p.model.form.ImageFilesModels;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

public class ShopPictureValidator implements Validator {
    private static final List<String> contentTypes = Arrays.asList("image/png", "image/jpeg", "image/gif");

    @Override
    public boolean supports(Class clazz) {
        return ImageFilesModels.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ImageFilesModels model = (ImageFilesModels) target;
        for (MultipartFile imageFile : model.getImageFiles()) {
            if(imageFile.getSize() <= 0) continue;
            String fileContentType = imageFile.getContentType();
            long size = imageFile.getSize();
            if (!contentTypes.contains(fileContentType)) {
                errors.rejectValue("imageFiles", "001", "Hình ảnh không đúng định dạng");
                break;
            }
        }
    }
}
