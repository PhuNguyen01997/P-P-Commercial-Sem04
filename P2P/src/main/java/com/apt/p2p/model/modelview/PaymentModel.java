package com.apt.p2p.model.modelview;

import com.apt.p2p.common.DateDeserializer;
import com.apt.p2p.entity.CardType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentModel {
    private Integer id;

    @NotBlank(message = "Họ tên không thể trống")
    private String fullname;

    @Pattern(regexp = "^[\\d\\s]+$", message = "Mã thẻ không hợp lệ")
    @NotBlank(message = "Mã thẻ không thể trống")
    private String number;

    @NotNull(message = "Vui lòng chọn loại thẻ")
    @Enumerated(EnumType.STRING)
    private CardType type;

    @JsonDeserialize(using = DateDeserializer.class)
    @NotNull(message = "Ngày hết hạn không thể trống")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date due;

    @NotNull(message = "CVV không thể trống")
    @Pattern(regexp = "^[\\d\\s]+$", message = "Cvv không hợp lệ")
    private String cvv;

    @NotBlank(message = "Địa chỉ không thể trống")
    private String addressRegister;

    @Pattern(regexp = "^[\\d\\s]+$", message = "Postal code không hợp lệ")
    @NotNull(message = "Postal code không thể trống")
    private String postalCode;

    private ShopModel shop;

    private UserModel user;
}
