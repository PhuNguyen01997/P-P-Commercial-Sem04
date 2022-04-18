package com.apt.p2p.model.form;

import com.apt.p2p.entity.Category;
import com.apt.p2p.entity.Product;
import com.apt.p2p.model.view.ProductModel;
import com.apt.p2p.model.view.RateModel;
import com.apt.p2p.model.view.ShopModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductForm extends ProductModel {
    private Map<String, MultipartFile> mapPictures;

    @Min(value = 1, message = "Invalid product category")
    private int categoryId;

    public ProductForm(ProductModel model) {
        super(model);
        this.mapPictures = new HashMap<>();
        this.categoryId = model.getCategory().getId();
    }
}
