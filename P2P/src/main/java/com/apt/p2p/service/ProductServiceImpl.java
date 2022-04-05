package com.apt.p2p.service;

import com.apt.p2p.common.FileUploadUtil;
import com.apt.p2p.common.StringProcessForView;
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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
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
        if (!filter.getName().isEmpty()) {
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

        if (filter.getCategoryId() != 0) {
            condition = condition.and(ProductSpecification.hasCategoryId(filter.getCategoryId()));
        }

        switch (filter.getStatus()) {
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
        Product entity = new Product(productForm);

        Category category = categoryRepository.findById(productForm.getCategoryId()).orElse(null);
        Shop shop = shopRepository.findById(shopId).orElse(null);

        List<String> fileNameList = new ArrayList<>();
        List<MultipartFile> imageFiles = new ArrayList<>(productForm.getMapPictures().values());
        for (MultipartFile file : imageFiles) {
            if (file.isEmpty()) continue;

            // save File to store
            String ranName = String.valueOf(new Date().getTime());
            String fileName = "shop_" + shop.getId() + "_" + ranName;
            String fileNameSaved = FileUploadUtil.saveFile(imageUploadDir, fileName, file);

            // save name to db
            if (fileNameSaved != null) {
                fileNameList.add(fileNameSaved);
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        String imageJson = null;
        try {
            imageJson = mapper.writeValueAsString(fileNameList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }

        entity.setShop(shop);
        entity.setCategory(category);
        entity.setImage(imageJson);

        productRepository.save(entity);

        return productMapper.productEntityToModel(entity);
    }

    @Override
    public ProductModel update(ProductForm productForm) {
        try {
            Product product = productRepository.findById(productForm.getId()).get();
            ObjectMapper mapper = new ObjectMapper();
            List<String> fileNameList = mapper.readValue(product.getImage(), new TypeReference<List<String>>() {
            });

            // user want to remove image
            List<String> deleteFileNameList = fileNameList.stream()
                    .filter(fileName -> !productForm.getMapPictures().keySet().contains(StringProcessForView.removeExtensionFilename(fileName)))
                    .collect(Collectors.toList());
            deleteFileNameList.forEach(fileName -> {
                FileUploadUtil.deleteFile(imageUploadDir, fileName);
            });

            // Process update imageFile
            List<String> addFileNameList = new ArrayList<>();
            productForm.getMapPictures().forEach((key, imageFile) -> {
                if (key.indexOf("new") == -1 && !imageFile.isEmpty()) {
                    String newExtension = FileUploadUtil.getExtensionName(imageFile).orElse(null);
                    String currentFullName = fileNameList.stream()
                            .filter(fullName -> StringProcessForView.removeExtensionFilename(fullName).equals(key))
                            .collect(Collectors.toList())
                            .get(0);
                    String currentExtension = FileUploadUtil.getExtensionName(currentFullName).get();
                    if (!newExtension.equals(currentExtension)) {
                        FileUploadUtil.deleteFile(imageUploadDir, currentFullName);
                        for (int i = 0; i < fileNameList.size(); i++) {
                            if(StringProcessForView.removeExtensionFilename(fileNameList.get(i)).equals(key)){
                                fileNameList.set(i, key + "." + newExtension);
                            }
                        }
                    }
                    FileUploadUtil.saveFile(imageUploadDir, key, imageFile);
                } else {
                    String ranName = String.valueOf(new Date().getTime());
                    String fileName = "shop_" + product.getShop().getId() + "_" + ranName;

                    String fileNameSaved = FileUploadUtil.saveFile(imageUploadDir, fileName, imageFile);

                    if (fileNameSaved != null) {
                        addFileNameList.add(fileNameSaved);
                    }
                }
            });

            fileNameList.removeAll(deleteFileNameList);
            fileNameList.addAll(addFileNameList);
            product.setImage(mapper.writeValueAsString(fileNameList));

            // Process update category
            Category category = categoryRepository.findById(productForm.getCategoryId()).get();
            product.setCategory(category);

            // Process update another info
            product.setName(productForm.getName());
            product.setPrice(productForm.getPrice());
            product.setDescription(productForm.getDescription());

            productRepository.save(product);
            return productMapper.productEntityToModel(product);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
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
    public List<Product> SonFindAllWithFilter(String keyword, BigDecimal minPrice, BigDecimal maxPrice, Integer
            rate, String sortBy, Boolean sortDirection) {
        // Sơn cặc xử lý cái biến "products" bao gồm các sản phẩm đã được lọc theo giá trị phái trên
        List<Product> products = null;

        // ------------------------------
        return products;
    }
}
