package com.apt.p2p.validate;

import com.apt.p2p.model.form.ImageFilesModels;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

public class PicturesValidator implements Validator {
    private static final List<String> contentTypes = Arrays.asList("image/png", "image/jpeg", "image/gif");

    @Override
    public boolean supports(Class<?> clazz) {
        return MultipartFile[].class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MultipartFile[] listImages = (MultipartFile[]) target;
        for (MultipartFile imageFile : listImages) {
            if(imageFile.getSize() <= 0) continue;
            String fileContentType = imageFile.getContentType();
//            long size = imageFile.getSize();
            if (!contentTypes.contains(fileContentType)) {
                errors.rejectValue("imageFiles", "001", "Hình ảnh không đúng định dạng");
                break;
            }
        }
    }
}
