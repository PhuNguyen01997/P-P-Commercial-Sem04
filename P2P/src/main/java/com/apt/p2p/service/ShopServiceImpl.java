package com.apt.p2p.service;

import com.apt.p2p.common.FileUploadUtil;
import com.apt.p2p.common.modelMapper.ShopMapper;
import com.apt.p2p.entity.Address;
import com.apt.p2p.entity.Shop;
import com.apt.p2p.entity.ShopTransaction;
import com.apt.p2p.entity.User;
import com.apt.p2p.model.view.ShopModel;
import com.apt.p2p.model.view.UserModel;
import com.apt.p2p.repository.AddressRepository;
import com.apt.p2p.repository.ShopRepository;
import com.apt.p2p.repository.ShopTransactionRepository;
import com.apt.p2p.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ShopTransactionRepository shopTransactionRepository;
    @Autowired
    private ShopMapper shopMapper;

    private String imgUploadDir = "shops/";

    @Override
    public ShopModel findByProductId(int productId) {
        ShopModel result = null;
        result = shopMapper.shopEntityToModel(shopRepository.findByProductId(productId));
        return result;
    }

    @Override
    public ShopModel findByOrderId(int orderId) {
        ShopModel result = null;
        Shop shop = shopRepository.findByOrderId(orderId);
        if (shop != null) {
            result = shopMapper.shopEntityToModel(shop);
        }
        return result;
    }

    @Override
    public ShopModel findById(int id) {
        Shop shop = shopRepository.findById(id).orElse(null);

        return shopMapper.shopEntityToModel(shop);
    }

    @Override
    @Transactional
    public boolean shopWithDraw(int shopId, BigDecimal amount) {
        boolean result = false;

        try {
            Shop shop = shopRepository.findById(shopId).get();

            ShopTransaction shopTransaction = new ShopTransaction(shop, amount);
            shopTransactionRepository.save(shopTransaction);

            shop.setFund(shop.getFund().subtract(amount));
            shopRepository.save(shop);

            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public ShopModel findByUserId(int userId) {
        Shop shop = shopRepository.findByUserId(userId);

        return shopMapper.shopEntityToModel(shop);
    }

    @Override
    public ShopModel createOrUpdate(ShopModel shopModel, MultipartFile logoFile, MultipartFile backgroundFile) {
        Shop shop = null;
        Address address = addressRepository.findById(shopModel.getAddress().getId()).get();

        try {
            if (shopModel.getId() == 0) {
                shop = new Shop(shopModel);
                User user = userRepository.findById(shopModel.getUser().getId()).get();
                shop.setUser(user);
            } else {
                shop = shopRepository.findById(shopModel.getId()).get();

                shop.setName(shopModel.getName());
                shop.setPhone(shopModel.getPhone());
                shop.setFund(shopModel.getFund());
                shop.setPermission(shopModel.getPermission());
                shop.setDescription(shopModel.getDescription());
                shop.setCreatedAt(shopModel.getCreatedAt());
                shop.setUpdatedAt(shopModel.getUpdatedAt());
            }

            shop.setAddress(address);

            if (!logoFile.isEmpty()) {
                String extension = FileUploadUtil.getExtensionName(logoFile).orElse(null);
                String fileName = shop.getLogo();
                if (fileName == null) {
                    fileName = "logo_" + String.valueOf(new Date().getTime()) + "." + extension;
                }
                else{
                    fileName.replaceAll("\\w+$", extension);
                }
                FileUploadUtil.saveFile(imgUploadDir, fileName, logoFile);

                shop.setLogo(fileName);
            }
            if (!backgroundFile.isEmpty()) {
                String extension = FileUploadUtil.getExtensionName(backgroundFile).orElse(null);
                String fileName = shop.getBackground();
                if (fileName == null) {
                    fileName = "thumbnal_" + String.valueOf(new Date().getTime()) + "." + extension;
                }
                else{
                    fileName = fileName.replaceAll("\\w+$", extension);
                }
                FileUploadUtil.saveFile(imgUploadDir, fileName, backgroundFile);

                shop.setBackground(fileName);
            }

            shopRepository.save(shop);
        } catch (IOException ioE) {
            ioE.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            shop = null;
        }

        return shopMapper.shopEntityToModel(shop);
    }
}
