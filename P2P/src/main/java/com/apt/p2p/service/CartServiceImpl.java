package com.apt.p2p.service;

import com.apt.p2p.common.modelMapper.CartMapper;
import com.apt.p2p.entity.Cart;
import com.apt.p2p.entity.Product;
import com.apt.p2p.entity.Shop;
import com.apt.p2p.entity.User;
import com.apt.p2p.model.modalform.ProductAddCartModel;
import com.apt.p2p.model.modelview.CartIndexViewModel;
import com.apt.p2p.model.modelview.CartModel;
import com.apt.p2p.model.modelview.ShopModel;
import com.apt.p2p.repository.CartRepository;
import com.apt.p2p.repository.ProductRepository;
import com.apt.p2p.repository.ShopRepository;
import com.apt.p2p.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private CartMapper mapper;

    @Override
    public List<CartModel> findByUserId(int userId) {
        return cartRepository.findByUserId(userId).stream().map(ce -> mapper.cartEntityToModel(ce)).collect(Collectors.toList());
    }

    @Override
    public CartModel save(ProductAddCartModel productAddCart) {
        Product product = productRepository.findById(productAddCart.getProductId()).get();

        User user = userRepository.findById(2).get();

        Cart cart = cartRepository.findByProductIdAndUserId(product.getId(), user.getId());
        if (cart != null) {
            cart.setQuantity(cart.getQuantity() + productAddCart.getQuantity());
            cart.setUpdatedAt(new Date());
        } else {
            cart = new Cart();
            cart.setUser(user);
            cart.setProduct(product);
            cart.setQuantity(productAddCart.getQuantity());
        }

        return mapper.cartEntityToModel(cartRepository.save(cart));
    }

    public List<CartIndexViewModel> getCartListChunkByShop() {
        int userId = 2;
        List<Shop> shopList = shopRepository.findHasCartByUserId(userId);
//        List<CartIndexViewModel> result = new ArrayList<>();
//        for (Shop shop : shopList) {
//            for (Product p: shop.getProducts()) {
//
//            }
//            shop.getProducts().stream().map(p -> {
//                List<Cart> carts = p.getCarts().stream().filter(c -> c.getUser().getId() == userId).collect(Collectors.toList());
//                List <CartModel> cartsModel = carts.stream().map(ce -> mapper.cartEntityToModel(ce)).collect(Collectors.toList());
//                result.add(new CartIndexViewModel(shop.getId(), cartsModel));
//            }).collect(Collectors.toList());
//        }
        return null;
    }
}
