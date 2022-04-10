package com.apt.p2p.controller;

import com.apt.p2p.entity.*;
import com.apt.p2p.common.RandomUtil;
import com.apt.p2p.model.view.DistrictModel;
import com.apt.p2p.model.view.ProvinceModel;
import com.apt.p2p.model.view.WardModel;
import com.apt.p2p.repository.*;
import com.apt.p2p.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class MainController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RateRepository rateRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private StatusOrderRepository statusOrderRepository;
    @Autowired
    private StatusHistoryRepository statusHistoryRepository;
    @Autowired
    private ShopTransactionRepository shopTransactionRepository;
    @Autowired
    private LocationService locationService;

    private String descriptionProduct = "Áo thun nam, Áo thun nữ, Áo phông nam, Áo phông nữ<br>" +
            "#aothun #aophong #aothunnam #aothunnu #aophongnam #aophongnu<br>" +
            "-------------------------------------------<br>" +
            "  ✅  MÔ TẢ SẢN PHẨM<br>" +
            "    ✪ Chất Liệu Vải : thun cotton 100%, co giãn 4 chiều, vải mềm, vải mịn, thoáng mát, không xù lông.<br>" +
            "    ✪ Kĩ thuật may: Đường may chuẩn chỉnh, tỉ mỉ, chắc chắn<br>" +
            "    ✪ Hình in: Công nghệ in tiên tiến đảm bảo độ bền màu và hình in ngay cả khi giặt máy.<br>" +
            "    ✪ Kiểu Dáng :Form Rộng Thoải Mái<br>" +
            "-------------------------------------------<br>" +
            "  ✅  THÔNG SỐ CHỌN SIZE<br>" +
            "    ✪ Size M:  1m40 - 1m60 (35-60kg) <br>" +
            "    ✪ Size L:  1m55 - 1m70 (50-70kg) <br>" +
            "    ✪ Size XL: 1m68 - 1m80 (60-80kg) <br>" +
            "(Bảng trên chỉ mang tính chất tham khảo, chọn mặc fom vừa vặn thoải mái, lên xuống size tuỳ theo sở thích ăn mặc của bạn)<br>" +
            "-------------------------------------------<br>" +
            "  ✅ SỬ DỤNG VÀ BẢO QUẢN<br>" +
            "    ✪  Lần đầu đem về chỉ xả nước lạnh rồi phơi khô cho sạch bụi vải<br>" +
            "    ✪  Chỉ giặt sau 3 ngày nhận áo<br>" +
            "    ✪  Nhớ lộn trái áo khi giặt và không giặt ngâm<br>" +
            "    ✪  Không giặt máy trong 2 tuần đầu<br>" +
            "    ✪  Không sử dụng thuốc tẩy<br>" +
            "    ✪  Khi phơi lộn trái và không phơi trực tiếp dưới ánh nắng mặt trời<br>" +
            "-------------------------------------------<br>" +
            "  ✅  SHOP CAM KẾT<br>" +
            "    ✪ Sản phẩm Áo thun nam, Áo thun nữ, Áo phông nam, Áo phông nữ <br>" +
            "    ✪ Tất cả hình ảnh áo thun nam nữ là ảnh thật do shop tự chụp và giữ bản quyền hình ảnh<br>" +
            "    ✪ Đảm bảo vải chất lượng 100%<br>" +
            "    ✪ Áo phông nam nữ đều được kiểm tra kĩ càng, cẩn thận và tư vấn nhiệt tình trước khi gói hàng giao cho Quý Khách<br>" +
            "    ✪ Hàng có sẵn, giao hàng ngay khi nhận được đơn <br>" +
            "    ✪ Hoàn tiền nếu sản phẩm Áo thun nam, Áo thun nữ, Áo phông nam, Áo phông nữ không giống với mô tả<br>" +
            "    ✪ Chấp nhận đổi hàng khi size không vừa<br>" +
            "    ✪ Giao hàng trên toàn quốc, nhận hàng trả tiền <br>" +
            "    ✪ Hỗ trợ đổi trả theo quy định của Shopee <br>" +
            "-------------------------------------------<br>" +
            "  ✅  QUYỀN LỢI CỦA BẠN<br>" +
            "    ✪ Chính sách bao đổi trả hàng miễn phí khi sản phẩm kém chất lượng và không giống hình, nhầm size, số lượng . <br>" +
            "    ✪ Khách hàng cũ : Mua lần thứ 2 trở đi sẽ được nhận mã giảm giá của shop <br>" +
            "-------------------------------------------<br>" +
            "  ✅  QUYỀN LỢI CỦA BẠN<br>" +
            "    ✪ Hotline: 0979913878  <br>" +
            "    ✪ Địa chỉ shop/ kho : Long Biên, Hà Nội <br>" +
            "    ✪ Đại chỉ nhà máy sản xuất : TP Nam Định<br>" +
            "--------------------------------------------<br>" +
            "#aothun #aophong #aothunnam #aothunnu #aophongnam #aophongnu #aothununisex #aophongunisex<br>" +
            "#aothuntaylo #aophongtaylo #aophongrong #aoformrong #aounisex #aopull #aodoi #ao #thun<br>" +
            "#streetwear #localbrand #basictee #freesize #hanquoc #formrong #oversize #ulzzang #unisex";

    private List<StatusOrder> statusOrders = null;
    private List<ProvinceModel> provinces = null;
    private Map<Integer, List<DistrictModel>> mapProvinceDistrict = new HashMap<>();
    private Map<Integer, List<WardModel>> mapDistrictWard = new HashMap<>();

    @GetMapping("shop/{shopId}/order/{orderId}")
    public String orderDetailShop(@PathVariable("shopId") int shopId, @PathVariable("orderId") int orderId) {
        return "user/account/order-detail";
    }

    @GetMapping("edit")
    public String userEdit() {
        return "user/account/user-form";
    }

    @GetMapping("shop/{shopId}")
    public String shopIndex() {
        return "user/main/shop-detail";
    }

    @GetMapping("identity")
    public String identity() {
        return "user/account/identity";
    }

    @GetMapping("seed")
    public String seed() {
        this.provinces = locationService.provinceFindAll();
        this.statusOrders = statusOrderRepository.findAll();

        List<Product> products = productRepository.findAll();
        List<User> users = userRepository.findAll();
        User testUser = users.stream().filter(u -> u.getUserId() == 1).collect(Collectors.toList()).get(0);
        users = users.stream().filter(u -> u.getUserId() != 1).collect(Collectors.toList());
        List<Category> categories = categoryRepository.findAll();

        // create users
        users.addAll(createUser(18));

        // create address for user
        users.forEach(u -> {
            Integer amount = RandomUtil.getRandomNumber(1, 2);
            List<Address> addresses = createAddress(u, amount);
            u.setAddresses(addresses);
        });

        // create shops
        List<Shop> shops = createShop(users, 17);
        shops.add(testUser.getShop());

        // product set description
        // product set shop
        Map<Integer, List<Shop>> mapCategoryShop = new HashMap<>();
        Integer ratio = shops.size() / categories.size();
        for (int i = 0; i < categories.size(); i++) {
            int range = (i + 1) * ratio;
            List<Shop> newShopList = new ArrayList<>();
            for (int j = range - ratio; j < range; j++) {
                newShopList.add(shops.get(j));
            }
            mapCategoryShop.put(i, newShopList);
        }
        products.forEach(p -> {
            // product set description
            p.setDescription(this.descriptionProduct);

            // product set shop
            List<Shop> shopValids = mapCategoryShop.get(p.getCategory().getId());
            p.setShop(shopValids.get(RandomUtil.getRandomNumber(0, ratio - 1)));
        });

        // create orders
        List<User> finalUsers = users;
        shops.forEach(shop -> {
            List<Order> asd = createOrders(shop, finalUsers, RandomUtil.getRandomNumber(5));
            String str = "asd";
        });

        userRepository.saveAll(users);
        productRepository.saveAll(products);

        System.out.println("Seed done");
        return null;
    }

    private List<Shop> createShop(List<User> users, int amount) {
        List<Shop> result = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            // lấy số user cuối -> đầu
            User user = users.get(users.size() - 1 - i);

            Shop newShop = new Shop();

            newShop.setLogo(i + "_logo.jpg");
            newShop.setBackground(i + "_thumbnail.jpg");
            newShop.setName(RandomUtil.getRandomString(10, 42));
            newShop.setPhone(RandomUtil.getRandomPhone());
            newShop.setPermission(true);
            newShop.setDescription("test");
            newShop.setUser(user);
            newShop.setAddress(user.getAddresses().get(RandomUtil.getRandomNumber(user.getAddresses().size() - 1)));

            result.add(newShop);
        }
        shopRepository.saveAll(result);

        return result;
    }

    private List<Rate> createRate(int amount) {
        for (int i = 0; i < amount; i++) {

        }
        return null;
    }

    private List<Address> createAddress(User user, int amount) {
        List<Address> result = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            Integer randomProvinceIndex = RandomUtil.getRandomNumber(0, this.provinces.size() - 1);
            ProvinceModel province = this.provinces.get(randomProvinceIndex);

            List<DistrictModel> districtList = getDistrictModels(province.getProvinceId());
            if (districtList.size() == 0) {
                randomProvinceIndex = RandomUtil.getRandomNumber(0, this.provinces.size() - 1);
                province = this.provinces.get(randomProvinceIndex);

                districtList = getDistrictModels(province.getProvinceId());
            }

            Integer randomDistrictIndex = RandomUtil.getRandomNumber(0, districtList.size() - 1);
            DistrictModel district = districtList.get(randomDistrictIndex);

            List<WardModel> wardList = getWardModels(district.getDistrictId());
            if (wardList.size() == 0) {
                randomDistrictIndex = RandomUtil.getRandomNumber(0, districtList.size() - 1);
                district = districtList.get(randomDistrictIndex);

                wardList = getWardModels(district.getDistrictId());
            }

            Integer randomWardIndex = RandomUtil.getRandomNumber(0, wardList.size() - 1);
            WardModel ward = wardList.get(randomWardIndex);

            Address newAddress = new Address();
            newAddress.setOwnName(RandomUtil.getRandomString(10, 42));
            newAddress.setOwnPhone(RandomUtil.getRandomPhone());
            newAddress.setNumber(RandomUtil.getRandomStringNumber(1, 3) + " " + RandomUtil.getRandomString(18, 60));
            newAddress.setWard(ward.getWardName());
            newAddress.setWardId(ward.getWardCode());
            newAddress.setDistrict(district.getDistrictName());
            newAddress.setDistrictId(district.getDistrictId());
            newAddress.setProvince(province.getProvinceName());
            newAddress.setProvinceId(province.getProvinceId());
            newAddress.setUser(user);

            result.add(newAddress);
        }

        addressRepository.saveAll(result);

        return result;
    }

    private List<User> createUser(int amount) {
        List<User> result = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            User newUser = new User();
            String name = "user_" + i;

            newUser.setEmail(name + "@gmail.com");
            newUser.setUsername(name);
            newUser.setPassword("$2a$10$lNNNx.dNQkWYxjaOJ3f2e.6L.rD89fl0f0tGyQXMwR/huHpCHMAZa");
            newUser.setPhone(RandomUtil.getRandomPhone());
            newUser.setAvatar(null);

            result.add(newUser);
        }

        userRepository.saveAll(result);

        return result;
    }

    private List<Order> createOrders(Shop shop, List<User> users, int amount) {
        List<Product> products = productRepository.findAllByShopId(shop.getId());
        List<Order> result = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            StatusOrder currentStatusOrder = this.statusOrders.get(RandomUtil.getRandomNumber(0, 3));
            User user = users.get(RandomUtil.getRandomNumber(users.size() - 1));
            Address address = user.getAddresses().get(user.getAddresses().size() - 1);

            Order newOrder = new Order();

            List<OrderDetail> orderDetails = createOrderDetails(products, newOrder, RandomUtil.getRandomNumber(1, 3));
            newOrder.setOrderDetails(orderDetails);

            BigDecimal shipping = BigDecimal.valueOf(RandomUtil.getRandomNumber(2, 200) * 1000);
            BigDecimal total = orderDetails.stream().map(OrderDetail::getSubtotal).reduce(BigDecimal.ZERO, BigDecimal::add).add(shipping);
            newOrder.setTotal(total);
            newOrder.setShippingCost(shipping);

            newOrder.setCurrentStatus(currentStatusOrder);
            newOrder.setMethodPayment(false);
            newOrder.setPercentPermission(0.05);
            newOrder.setUser(user);
            newOrder.setShop(shop);
            newOrder.setAddress(address);
            newOrder.setStripeCardId(null);
            orderRepository.save(newOrder);
            orderDetailRepository.saveAll(orderDetails);

            ShopTransaction transaction = new ShopTransaction(shop, newOrder);
            shopTransactionRepository.save(transaction);
            newOrder.setShopTransaction(transaction);

            List<StatusHistory> statusHistories = this.statusOrders
                    .stream().filter(so -> so.getId() <= currentStatusOrder.getId())
                    .map(so -> {
                        StatusHistory statusHistory = new StatusHistory(so, newOrder);
                        statusHistory.setOrder(newOrder);
                        return statusHistory;
                    })
                    .collect(Collectors.toList());
            statusHistoryRepository.saveAll(statusHistories);
            newOrder.setStatusHistories(statusHistories);

            result.add(newOrder);
        }

        return result;
    }

    private List<OrderDetail> createOrderDetails(List<Product> products, Order order, int amount) {
        List<OrderDetail> result = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            if (products.size() == 0) break;

            Product product = products.get(RandomUtil.getRandomNumber(products.size() - 1));
            products.remove(product);

            OrderDetail newOrderDetail = new OrderDetail(product.getPrice(), RandomUtil.getRandomNumber(1, 3));
            newOrderDetail.setProduct(product);
            newOrderDetail.setOrder(order);

            result.add(newOrderDetail);
        }

        return result;
    }

    private List<DistrictModel> getDistrictModels(Integer provinceId) {
        List<DistrictModel> districtList = new ArrayList<>();
        if (mapProvinceDistrict.get(provinceId) == null) {
            districtList = locationService.districtFindAllByProvinceId(provinceId);
            if (districtList.size() != 0) {
                mapProvinceDistrict.put(provinceId, districtList);
            }
        } else {
            districtList = mapProvinceDistrict.get(provinceId);
        }

        return districtList;
    }

    private List<WardModel> getWardModels(Integer districtId) {
        List<WardModel> wardList = new ArrayList<>();
        if (mapDistrictWard.get(districtId) == null) {
            wardList = locationService.wardFindAllByDistrictId(districtId);
            if (wardList.size() != 0) {
                mapDistrictWard.put(districtId, wardList);
            }
        } else {
            wardList = mapDistrictWard.get(districtId);
        }
        return wardList;
    }
}
