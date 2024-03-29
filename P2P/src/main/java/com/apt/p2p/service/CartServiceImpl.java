package com.apt.p2p.service;

import com.apt.p2p.common.modelMapper.CartMapper;
import com.apt.p2p.common.modelMapper.ProductMapper;
import com.apt.p2p.common.modelMapper.ShopMapper;
import com.apt.p2p.entity.Cart;
import com.apt.p2p.entity.Product;
import com.apt.p2p.entity.Shop;
import com.apt.p2p.entity.User;
import com.apt.p2p.model.form.ProductAddCartModel;
import com.apt.p2p.model.view.*;
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
    private ShopRepository shopRepository;

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<CartModel> findAllByUserId(int userId) {
        return cartRepository.findAllByUserId(userId).stream().map(ce -> cartMapper.cartEntityToModel(ce)).collect(Collectors.toList());
    }

    @Override
    public CartModel save(int userId, ProductAddCartModel productAddCart) throws Exception {
        Product product = productRepository.findById(productAddCart.getProductId()).get();

        Shop shop = shopRepository.findByProductId(productAddCart.getProductId());
        if(shop.getUser().getUserId() == userId){
            throw new Exception("You cannot add your product to your cart !!!");
        }

        User user = userRepository.findById(userId).get();

        Cart cart = cartRepository.findByProductIdAndUserId(product.getId(), user.getUserId());
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

    @Override
    public List<CartIndexViewModel> getCartListChunkByShop(int userId) {
        List<Cart> cartList = cartRepository.findAllByUserId(userId);
        List<CartIndexViewModel> result = new ArrayList<>();
        for (Cart cart : cartList) {
            Integer index = IntStream.range(0, result.size()).filter(i -> result.get(i).getShop().getId() == cart.getProduct().getShop().getId()).findFirst().orElse(-1);

            ShopModel shopModel = shopMapper.shopEntityToModel(cart.getProduct().getShop());
            ProductModel productModel = productMapper.productEntityToModel(cart.getProduct());
            CartModel cartModel = cartMapper.cartEntityToModel(cart);

            if (index >= 0) {
                CartIndexViewModel cartIndexViewModel = result.get(index);

                List<ProductCartModel> productCartModelList = cartIndexViewModel.getProductCarts();
                productCartModelList.add(new ProductCartModel(productModel, cartModel));
                cartIndexViewModel.setProductCarts(productCartModelList);

                result.set(index, cartIndexViewModel);
            } else {
                List<ProductCartModel> productCartModelList = new ArrayList<ProductCartModel>();
                productCartModelList.add(new ProductCartModel(productModel, cartModel));

                CartIndexViewModel cartIndexViewModel = new CartIndexViewModel(shopModel, productCartModelList);

                result.add(cartIndexViewModel);
            }
        }

        return result;
    }

    @Override
    public CartIndexViewModel getCartProductByShopIdAndCartId(int shopId, List<Integer> cartIdList){
        CartIndexViewModel result = new CartIndexViewModel();
        List<ProductCartModel> productCartModelList = new ArrayList<>();
        result.setShop(shopMapper.shopEntityToModel(shopRepository.findById(shopId).get()));

        for (Integer cardId: cartIdList) {
            ProductModel productModel = productMapper.productEntityToModel(productRepository.findByCartId(cardId));
            CartModel cartModel = cartMapper.cartEntityToModel(cartRepository.findById(cardId).get());

            ProductCartModel productCartModel = new ProductCartModel(productModel, cartModel);
            productCartModelList.add(productCartModel);
        }

        result.setProductCarts(productCartModelList);

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
