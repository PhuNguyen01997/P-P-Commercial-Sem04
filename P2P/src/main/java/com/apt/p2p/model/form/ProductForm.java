package com.apt.p2p.model.form;

import com.apt.p2p.model.view.ProductModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductForm extends ProductModel {
    private Map<String, MultipartFile> mapPictures;
    @Min(value = 1, message = "Danh mục sản phẩm không hợp lệ")
    private int categoryId;
}
