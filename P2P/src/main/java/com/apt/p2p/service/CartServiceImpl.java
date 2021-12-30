package com.apt.p2p.service;

import com.apt.p2p.common.modelMapper.CartMapper;
import com.apt.p2p.common.modelMapper.ProductMapper;
import com.apt.p2p.common.modelMapper.ShopMapper;
import com.apt.p2p.entity.Cart;
import com.apt.p2p.entity.Product;
import com.apt.p2p.entity.User;
import com.apt.p2p.model.modalform.ProductAddCartModel;
import com.apt.p2p.model.modelview.*;
import com.apt.p2p.repository.CartRepository;
import com.apt.p2p.repository.ProductRepository;
import com.apt.p2p.repository.ShopRepository;
import com.apt.p2p.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<CartModel> findByUserId(int userId) {
        return cartRepository.findByUserId(userId).stream().map(ce -> cartMapper.cartEntityToModel(ce)).collect(Collectors.toList());
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

        return cartMapper.cartEntityToModel(cartRepository.save(cart));
    }

    public List<CartIndexViewModel> getCartListChunkByShop() {
        int userId = 2;
        List<Cart> cartList = cartRepository.findByUserId(userId);
        List<CartIndexViewModel> result = new ArrayList<>();
        cartList = cartList.stream()
                .sorted((c1, c2) ->
                        c1.getProduct().getShop().getId().compareTo(c2.getProduct().getShop().getId())
                ).collect(Collectors.toList());
        for (Cart cart : cartList) {
            Integer index = IntStream.range(0, result.size()).filter(i -> result.get(i).getShop().getId() == cart.getProduct().getShop().getId()).findFirst().orElse(-1);

            if (index >= 0) {
                ShopModel shopModel = shopMapper.shopEntityToModel(cart.getProduct().getShop());
                ProductModel productModel = productMapper.productEntityToModel(cart.getProduct());
                CartModel cartModel = cartMapper.cartEntityToModel(cart);

                CartIndexViewModel cartIndexViewModel = result.get(index);

                List<ProductCartModel> productCartModelList = cartIndexViewModel.getProductCarts();
                productCartModelList.add(new ProductCartModel(productModel, cartModel));
                cartIndexViewModel.setProductCarts(productCartModelList);

                result.set(index, cartIndexViewModel);
            } else {
                ShopModel shopModel = shopMapper.shopEntityToModel(cart.getProduct().getShop());
                ProductModel productModel = productMapper.productEntityToModel(cart.getProduct());
                CartModel cartModel = cartMapper.cartEntityToModel(cart);

                List<ProductCartModel> productCartModelList = new ArrayList<ProductCartModel>();
                productCartModelList.add(new ProductCartModel(productModel, cartModel));

                CartIndexViewModel cartIndexViewModel = new CartIndexViewModel(shopModel, productCartModelList);

                result.add(cartIndexViewModel);
            }
        }

        return result;
    }

    @Override
    public void delete(int id) {
        cartRepository.deleteById(id);
    }

    @Override
    public void deleteAllById(List<Integer> idList) {
        cartRepository.deleteAllById(idList);
    }

    @Override
    public boolean edit(CartModel cartModel) {
        Cart cart = cartRepository.findById(cartModel.getId()).get();
        if (cart != null) {
            cart.setQuantity(cartModel.getQuantity());
            cartRepository.save(cart);
            return true;
        }
        return false;
    }
}
