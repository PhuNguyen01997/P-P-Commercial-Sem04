package com.apt.p2p.validate;

import com.apt.p2p.model.form.ProductForm;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

public class ProductFormValidator implements Validator {
    private static final List<String> contentTypes = Arrays.asList("image/png", "image/jpeg", "image/gif");

    @Override
    public boolean supports(Class<?> clazz) {
        return ProductForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductForm model = (ProductForm) target;
        for (MultipartFile imageFile : model.getMapPictures().values()) {
            if(imageFile.getSize() <= 0) continue;
            String fileContentType = imageFile.getContentType();
            if (!contentTypes.contains(fileContentType)) {
                errors.rejectValue("mapPictures", "001", "Hình ảnh không đúng định dạng");
                break;
            }
        }
    }
}
