package com.apt.p2p.controller;

import com.apt.p2p.common.FileUploadUtil;
import com.apt.p2p.entity.*;
import com.apt.p2p.common.RandomUtil;
import com.apt.p2p.entityEnum.ShopTransactionStatus;
import com.apt.p2p.model.view.DistrictModel;
import com.apt.p2p.model.view.ProvinceModel;
import com.apt.p2p.model.view.UserModel;
import com.apt.p2p.model.view.WardModel;
import com.apt.p2p.repository.*;
import com.apt.p2p.service.LocationService;
import com.apt.p2p.service.UserService;
import com.apt.p2p.service.UsersDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transaction;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
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
    private CartRepository cartRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private LocationService locationService;
    @Autowired
    private UsersDetailServiceImpl usersDetailService;
    @Autowired
    private UserService service;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private String imgUploadDir = "users/";

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
    private List<Role> roles;

    @GetMapping("account")
    public String userEdit(Model model) {
        UserModel user = usersDetailService.getCurrentUser();
        model.addAttribute("user" , user);
        return "user/account/user-form";
    }

    @PostMapping("edit-account")
    public String updateAccount(Model model,
                                @RequestParam("pic") MultipartFile image,
                                @Valid @ModelAttribute("user") UserModel userModel,
                                BindingResult result) {

        User usr = userRepository.findByUsername(userModel.getUsername());

        if (!image.isEmpty()) { // new image
            String extension = FileUploadUtil.getExtensionName(image).orElse(null);
            String fileName = usr.getAvatar();
            if (fileName == null) {
                fileName =String.valueOf(new Date().getTime()) + '.' + extension;
            } else if (extension != FileUploadUtil.getExtensionName(fileName).orElse(null)) {
                FileUploadUtil.deleteFile("", fileName);
                fileName = fileName.replaceAll("\\w+$", extension);
            }

            FileUploadUtil.saveFile(imgUploadDir, String.valueOf(fileName).replaceAll("\\.\\w+$", ""), image);

            usr.setAvatar(fileName);
        }
        usr.setUsername(usr.getUsername());
        usr.setEmail(userModel.getEmail());
        usr.setPassword(usr.getPassword());
        usr.setPhone(userModel.getPhone());
        usr.setUsername(userModel.getUsername());
        usr.setRoles(usr.getRoles());
        service.save(usr);
        return "redirect:/account";
    }

    @GetMapping("identity")
    public String identity() {
        return "user/account/identity";
    }

    @GetMapping("seed")
    public String seed() {
        this.provinces = locationService.provinceFindAll();
        this.statusOrders = statusOrderRepository.findAll();
        this.roles = roleRepository.findAll();

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
        users.add(testUser);

        // create transaction withdraw
        shops.forEach(shop -> {
            List<ShopTransaction> transactions = createShopTransaction(shop, RandomUtil.getRandomNumber(100));
            shop.setShopTransactions(transactions);
        });

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
            mapCategoryShop.put(i + 1, newShopList);
        }
        products.forEach(p -> {
            // product set description
            p.setDescription(this.descriptionProduct);
            LocalDateTime localDateTime = LocalDateTime.of(2022, 4, 4, RandomUtil.getRandomNumber(23), RandomUtil.getRandomNumber(59), 0);
            p.setCreatedAt(Timestamp.valueOf(localDateTime));

            // product set shop
            List<Shop> shopValids = mapCategoryShop.get(p.getCategory().getId());
            p.setShop(shopValids.get(RandomUtil.getRandomNumber(0, ratio - 1)));
        });
        productRepository.saveAll(products);
        shops = shopRepository.findAll();

        // create orders
        List<Order> orders = new ArrayList<>();
        List<User> finalUsers = users;
        shops.forEach(shop -> {
            List<Order> newOrders = createOrders(shop, finalUsers, RandomUtil.getRandomNumber(10, 30));
            shop.setOrders(newOrders);
            orders.addAll(newOrders);
        });

        // create rates
        List<Rate> rates = new ArrayList<>();
        orders.forEach(order -> {
            if (order.getCurrentStatus().getId() == 6) {
                order.getOrderDetails().forEach(orderDetail -> {
                    Rate newRate = new Rate();
                    newRate.setDescription(RandomUtil.getRandomParagraph(RandomUtil.getRandomNumber(25, 60)));
                    newRate.setStar(RandomUtil.getRandomNumber(1, 5));
                    newRate.setUser(order.getUser());
                    newRate.setProduct(orderDetail.getProduct());

                    rates.add(newRate);
                });
            }
        });
        rateRepository.saveAll(rates);

        // create carts
        users.forEach(u -> {
            Integer randomAmount = RandomUtil.getRandomNumber(3, products.size() / 2);
            List<Cart> carts = createCarts(u, new ArrayList<>(products), randomAmount);
            u.setCarts(carts);
        });

        // create roles users
        users.forEach(user -> {
            List<Role> newRoles = new ArrayList<>();
            newRoles.add(this.roles.get(0));
            if (user.getUsername().equals("seller") || user.getShop() != null) {
                newRoles.add(this.roles.get(2));
            } else if (user.getUsername().equals("admin")) newRoles.add(this.roles.get(1));
            user.setRoles(newRoles);
        });

        userRepository.saveAll(users);

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
            newShop.setName(RandomUtil.getRandomParagraph(RandomUtil.getRandomNumber(4)));
            newShop.setPhone(RandomUtil.getRandomPhone());
            newShop.setPermission(true);
            newShop.setDescription("test");
            newShop.setUser(user);
            newShop.setFund(BigDecimal.valueOf(RandomUtil.getRandomNumber(350000000)));
            newShop.setAddress(user.getAddresses().get(RandomUtil.getRandomNumber(user.getAddresses().size() - 1)));

            result.add(newShop);
            user.setShop(newShop);
        }

        shopRepository.saveAll(result);

        return result;
    }

    private List<Address> createAddress(User user, int amount) {
        List<Address> result = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            Integer randomProvinceIndex = RandomUtil.getRandomNumber(0, this.provinces.size() - 1);
            ProvinceModel province = this.provinces.get(randomProvinceIndex);

            List<DistrictModel> districtList = getDistrictModels(province.getProvinceId());
            while (districtList.size() == 0) {
                randomProvinceIndex = RandomUtil.getRandomNumber(0, this.provinces.size() - 1);
                province = this.provinces.get(randomProvinceIndex);

                districtList = getDistrictModels(province.getProvinceId());
            }

            Integer randomDistrictIndex = RandomUtil.getRandomNumber(0, districtList.size() - 1);
            DistrictModel district = districtList.get(randomDistrictIndex);

            List<WardModel> wardList = getWardModels(district.getDistrictId());
            while (wardList.size() == 0) {
                randomDistrictIndex = RandomUtil.getRandomNumber(0, districtList.size() - 1);
                district = districtList.get(randomDistrictIndex);

                wardList = getWardModels(district.getDistrictId());
            }

            Integer randomWardIndex = RandomUtil.getRandomNumber(0, wardList.size() - 1);
            WardModel ward = wardList.get(randomWardIndex);

            Address newAddress = new Address();
            newAddress.setOwnName(RandomUtil.getRandomString(10, 42));
            newAddress.setOwnPhone(RandomUtil.getRandomPhone());
            newAddress.setNumber(RandomUtil.getRandomStringNumber(1, 3) + " " + RandomUtil.getRandomParagraph(RandomUtil.getRandomNumber(2, 4)));
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

        if (products.size() == 0) return result;

        for (int i = 0; i < amount; i++) {
            Integer randomStatusIndex = RandomUtil.getRandomNumber(5);
            if(randomStatusIndex <= 3){
                randomStatusIndex = RandomUtil.getRandomNumber(5);
            }
            if(randomStatusIndex == 4){
                randomStatusIndex = RandomUtil.getRandomNumber(4, 5);
            }
            StatusOrder currentStatusOrder = this.statusOrders.get(randomStatusIndex);
            User user = users.get(RandomUtil.getRandomNumber(users.size() - 1));
            Address address = user.getAddresses().get(user.getAddresses().size() - 1);

            Order newOrder = new Order();

            List<OrderDetail> orderDetails = createOrderDetails(new ArrayList<>(products), newOrder, RandomUtil.getRandomNumber(1, 3));
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
            orderDetails.forEach(od -> od.setOrder(newOrder));
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

            result.add(newOrderDetail);
        }

        orderDetailRepository.saveAll(result);
        return result;
    }

    private List<ShopTransaction> createShopTransaction(Shop shop, int amount) {
        List<ShopTransaction> result = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            ShopTransaction newTransaction = new ShopTransaction(shop, BigDecimal.valueOf(RandomUtil.getRandomNumber(5000000)), ShopTransactionStatus.values()[RandomUtil.getRandomNumber(2)]);

            result.add(newTransaction);
        }

        shopTransactionRepository.saveAll(result);

        return result;
    }

    private List<Cart> createCarts(User user, List<Product> products, int amount) {
        List<Cart> result = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            Integer randomIndexProduct = RandomUtil.getRandomNumber(products.size() - 1);
            Product product = products.get(randomIndexProduct);
            products.remove(product);

            Cart newCart = new Cart();
            newCart.setQuantity(RandomUtil.getRandomNumber(1, 3));
            newCart.setUser(user);
            newCart.setProduct(product);

            result.add(newCart);
        }

        cartRepository.saveAll(result);

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
