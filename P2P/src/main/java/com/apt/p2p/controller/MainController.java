package com.apt.p2p.controller;

import com.apt.p2p.common.RandomUtil;
import com.apt.p2p.entity.Address;
import com.apt.p2p.entity.Product;
import com.apt.p2p.entity.Rate;
import com.apt.p2p.entity.User;
import com.apt.p2p.model.view.DistrictModel;
import com.apt.p2p.model.view.ProvinceModel;
import com.apt.p2p.model.view.WardModel;
import com.apt.p2p.repository.*;
import com.apt.p2p.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    private List<ProvinceModel> provinces = null;

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

        List<Product> products = productRepository.findAll();
        List<User> users = userRepository.findAll();

        // product set description
        products.forEach(p -> {
            p.setDescription(this.descriptionProduct);
        });

        // create address for user
        users.forEach(u -> {
//            Integer amount = RandomUtil.getRandomNumber(1, 2);
//            List<Address> addresses = createAddress(u, amount);
        });

        userRepository.saveAll(users);
        productRepository.saveAll(products);

        System.out.println("Seed done");
        return null;
    }

    private List<Rate> createRate(Integer amount) {
        for (int i = 0; i < amount; i++) {

        }
        return null;
    }

    private List<Address> createAddress(User user, Integer amount) {
        List<Address> result = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            Integer randomProvinceIndex = RandomUtil.getRandomNumber(0, this.provinces.size() - 1);
            ProvinceModel province = this.provinces.get(randomProvinceIndex);

            List<DistrictModel> districtList = locationService.districtFindAllByProvinceId(province.getProvinceId());
            Integer randomDistrictIndex = RandomUtil.getRandomNumber(0, districtList.size() - 1);
            DistrictModel district = districtList.get(randomDistrictIndex);

            List<WardModel> wardList = locationService.wardFindAllByDistrictId(district.getDistrictId());
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
}
