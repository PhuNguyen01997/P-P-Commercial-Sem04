package com.apt.p2p.service;

import com.apt.p2p.common.FileUploadUtil;
import com.apt.p2p.common.modelMapper.ProductMapper;
import com.apt.p2p.entity.Category;
import com.apt.p2p.entity.Product;
import com.apt.p2p.entity.Shop;
import com.apt.p2p.model.form.FilterProductPortal;
import com.apt.p2p.model.form.ProductForm;
import com.apt.p2p.model.view.ProductModel;
import com.apt.p2p.repository.CategoryRepository;
import com.apt.p2p.repository.ProductRepository;
import com.apt.p2p.repository.ProductSpecification;
import com.apt.p2p.repository.ShopRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private ProductMapper productMapper;

    private String imageUploadDir = "products/";

    @Override
    public List<ProductModel> findAllByShopId(int shopId) {
        return productRepository.findAllByShopId(shopId).stream().map(pe -> productMapper.productEntityToModel(pe)).collect(Collectors.toList());
    }

    @Override
    public ProductModel findByOrderDetailId(int orderDetailId) {
        return productMapper.productEntityToModel(productRepository.findByOrderDetailId(orderDetailId));
    }

    @Override
    public Integer countByShopId(int shopId) {
        return productRepository.countByShopId(shopId);
    }

    @Override
    public ProductModel findById(int id) {
        ProductModel result = null;
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            result = productMapper.productEntityToModel(product.get());
        }
        return result;
    }

    @Override
    public List<ProductModel> findAllByShopWithFilterPortal(int shopId, FilterProductPortal filter) {
        Specification<Product> condition = Specification.where(ProductSpecification.hasShopId(shopId));
        if(!filter.getName().isEmpty()){
            switch (filter.getFilterBy()) {
                case 1: {
                    condition = condition.and(ProductSpecification.hasName(filter.getName()));
                    break;
                }
                case 2: {
                    condition = condition.and(ProductSpecification.hasId(filter.getName()));
                    break;
                }
            }
        }

        if(filter.getCategoryId() != 0){
            condition = condition.and(ProductSpecification.hasCategoryId(filter.getCategoryId()));
        }

        switch (filter.getStatus()){
            case 1: {
                condition = condition.and(ProductSpecification.hasStock(true));
                break;
            }
            case 2: {
                condition = condition.and(ProductSpecification.hasStock(false));
                break;
            }
        }

        List<Product> productEntities = productRepository.findAll(condition);

        List<ProductModel> productModels = productEntities.stream()
                .map(e -> productMapper.productEntityToModel(e))
                .collect(Collectors.toList());

        return productModels;
    }

    @Override
    public ProductModel create(ProductForm productForm, int shopId) {
        try {
            Product entity = new Product(productForm);

            Category category = categoryRepository.findById(productForm.getCategoryId()).orElse(null);
            Shop shop = shopRepository.findById(shopId).orElse(null);

            String ranName = String.valueOf(new Date().getTime());
            List<String> fileNameList = new ArrayList<>();

            int index = -1;
            List<MultipartFile> imageFiles = new ArrayList<>(productForm.getMapPictures().values());
            for (MultipartFile file : imageFiles) {
                if(file.isEmpty()) continue;
                index++;
                // save File to store
                String extension = FileUploadUtil.getExtensionName(file).orElse(null);
                String fileName = "shop_" + shop.getId() + "_" + ranName + "_" + index + "." + extension;
                FileUploadUtil.saveFile(imageUploadDir, fileName, file);

                // save name to db
                fileNameList.add(fileName);
            }
            ObjectMapper mapper = new ObjectMapper();
            String imageJson = mapper.writeValueAsString(fileNameList);

            entity.setShop(shop);
            entity.setCategory(category);
            entity.setImage(imageJson);

            productRepository.save(entity);

            return productMapper.productEntityToModel(entity);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ProductModel update(ProductForm productForm) {
        return null;
    }

    @Override
    public ProductModel findByShopIdAndProductId(int shopId, int productId) {
        ProductModel result = null;
        Product product = productRepository.findByShopIdAndProductId(shopId, productId);
        if (product != null) {
            result = productMapper.productEntityToModel(product);
        }
        return result;
    }

    @Override
    public List<Product> SonFindAllWithFilter(String keyword, BigDecimal minPrice, BigDecimal maxPrice, Integer rate, String sortBy, Boolean sortDirection) {
        // Sơn cặc xử lý cái biến "products" bao gồm các sản phẩm đã được lọc theo giá trị phái trên
        List<Product> products = null;

        // ------------------------------
        return products;
    }
}
